package com.example.testlist.Api

import com.example.testlist.Model.Listmodel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ListDataService {

    private val BASEURL = "https://dl.dropboxusercontent.com"

    private val api: ListDataApi

    init {

        api = Retrofit.Builder().baseUrl(BASEURL).addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
            .create(ListDataApi::class.java)
    }


    fun getList(): Single<Listmodel> {
        return api.getListdata()
    }

}