package com.example.testlist.Core

import android.app.Application
import com.example.testlist.Database.AppDatabase


//Core Application class
class TaskApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        AppDatabase.invoke(this)

    }

    companion object {
        lateinit var instance: TaskApplication
            private set
    }
}