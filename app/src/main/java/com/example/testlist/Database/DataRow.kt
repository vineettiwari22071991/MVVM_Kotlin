package com.example.testlist.Database

import androidx.room.TypeConverter
import com.example.testlist.Model.Row
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class DataRow {

    //Type Converter Fromlist to String
    @TypeConverter
    fun fromList(itemlist: ArrayList<Row>?): String? {
        if (itemlist == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<ArrayList<Row>>() {

        }.getType()
        return gson.toJson(itemlist, type)
    }

    //Type Converter toList from String
    @TypeConverter
    fun toList(itemlist: String?): ArrayList<Row>? {
        if (itemlist == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<ArrayList<Row>>() {

        }.getType()
        return gson.fromJson<ArrayList<Row>>(itemlist, type)
    }
}


