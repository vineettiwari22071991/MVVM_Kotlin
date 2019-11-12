package com.example.testlist.Api

import com.example.testlist.Model.Listmodel
import io.reactivex.Single
import retrofit2.http.GET

interface ListDataApi {

    @GET("/s/2iodh4vg0eortkl/facts.json")
    fun getListdata():Single<Listmodel>
}