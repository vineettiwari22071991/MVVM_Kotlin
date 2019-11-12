package com.example.testlist.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testlist.Adapter.ListviewAdapter
import com.example.testlist.Model.Listmodel
import com.example.testlist.R
import com.example.testlist.Util.isInternetAvailable
import com.example.testlist.Viewmodel.MainActivityviewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    lateinit var viewModel: MainActivityviewModel

    private val listadapter = ListviewAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainActivityviewModel::class.java)


        if (isInternetAvailable(this))
            viewModel.refresh()
        else
            Toast.makeText(this, getString(R.string.internet), Toast.LENGTH_SHORT).show()


        swipe.setOnRefreshListener {
            swipe.isRefreshing = false
            if (isInternetAvailable(this))
                viewModel.refresh()
            else
                Toast.makeText(this, getString(R.string.internet), Toast.LENGTH_SHORT).show()
        }


        rv_list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listadapter
        }

        ObserverViewmodel()

    }


    fun ObserverViewmodel() {

        viewModel.listdata.observe(this, Observer { Listdata: Listmodel? ->
            Listdata?.let {
                swipe.visibility = View.VISIBLE
                rv_list.visibility = View.VISIBLE
                listadapter.updateList(Listdata.rows)
                supportActionBar!!.title = Listdata.title
            }
        })

        viewModel.listerror.observe(this, Observer { isError: Boolean? ->

            isError?.let {

                //  swipe.visibility = View.GONE
                // rv_list.visibility = View.GONE
                //Toast.makeText(this, "Something went Wrong...", Toast.LENGTH_SHORT).show()

            }
        })

        viewModel.loading.observe(this, Observer { isLoading: Boolean? ->

            isLoading?.let {
                progress.visibility = if (it) View.VISIBLE else View.GONE
                {
                    if (it) {
                        swipe.visibility = View.GONE
                        rv_list.visibility = View.GONE

                    }
                }
            }
        })

    }
}
