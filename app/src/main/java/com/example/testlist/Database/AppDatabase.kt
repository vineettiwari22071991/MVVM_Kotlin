package com.example.testlist.Database

import android.app.Application
import androidx.room.*


@Database(
    entities = [TitleDb::class],
    version = 1
)
@TypeConverters(DataRow::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun ListDao(): ListDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Application) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Application) = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "task_list.db"
        ).allowMainThreadQueries()
            .build()
    }
}