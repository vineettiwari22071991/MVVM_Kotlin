package com.example.testlist.Viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testlist.Api.ListDataService
import com.example.testlist.Model.Listmodel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import com.example.testlist.Model.Row


class MainActivityviewModel : ViewModel() {


    private val ListDataService = ListDataService()

    private val disposable = CompositeDisposable()

    val listdata = MutableLiveData<Listmodel>()

    val listerror = MutableLiveData<Boolean>()

    val loading = MutableLiveData<Boolean>()


    fun refresh() {

        fecthdatafromserver()
    }


    private fun fecthdatafromserver() {

        loading.value = true

        disposable.add(
            ListDataService.getList().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(object :
                    DisposableSingleObserver<Listmodel>() {
                    override fun onSuccess(t: Listmodel) {
                        loading.value = false
                        val toRemove = ArrayList<Row>()
                        for (item in t.rows) {
                            if (item.description.isNullOrBlank() && item.imageHref.isNullOrBlank() && item.title.isNullOrBlank()) {
                                toRemove.add(item)
                            }
                        }


                        t.rows.removeAll(toRemove)
                        listdata.value = t
                        listerror.value = false
                    }

                    override fun onError(e: Throwable) {
                        loading.value = false
                        listerror.value = true
                    }
                })
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}