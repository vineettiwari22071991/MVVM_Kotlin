package com.example.testlist.Database

import com.example.testlist.Model.Listmodel
import com.example.testlist.Core.TaskApplication


class DatabaseRepo {

    val db = AppDatabase(TaskApplication.instance)

    fun additem(listitem: Listmodel) {
        val listdata = TitleDb(1, listitem.title, listitem.rows)

        db.ListDao().DeleteAll(listdata)
        db.ListDao().insertAll(listdata)

    }

    fun getListAll(): Listmodel? {
        val listdata = db?.ListDao()?.getAll()?.row?.let {
            db.ListDao().getAll()?.title?.let { it1 ->
                Listmodel(
                    it,
                    it1
                )
            }
        }
        return listdata
    }

}