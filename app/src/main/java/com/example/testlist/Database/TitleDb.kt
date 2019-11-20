package com.example.testlist.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.testlist.Model.Row

@Entity(tableName = "Titletable")
data class TitleDb(

    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "row")
    @TypeConverters(DataRow::class)
    var row: ArrayList<Row>
)