package com.example.testlist.Database

import androidx.room.*

@Dao
interface ListDao {
    @Query("SELECT * FROM Titletable")
    fun getAll(): TitleDb?


    @Insert
    fun insertAll(vararg item: TitleDb)

    @Delete
    fun DeleteAll(vararg item: TitleDb)

}