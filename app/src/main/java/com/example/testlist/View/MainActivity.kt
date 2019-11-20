package com.example.testlist.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testlist.R


class MainActivity : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager
    private val loadlistfragment = ListviewFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Load Fragment in Activity
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fl_fragment, loadlistfragment)
        fragmentTransaction.commit()

    }

    //Set Toolbar Title
    fun settoobarTitle(value: String) {
        supportActionBar!!.title = value
    }

}
