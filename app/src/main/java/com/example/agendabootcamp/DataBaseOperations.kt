package com.example.agendabootcamp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseOperations(context:Context):SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {
    companion object{
        const val DATABASE_NAME="TodoItems.db"
        const val DATABASE_VERSION=1
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(DataBaseInfo.SQL_CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        if (p1!=p2){
            p0?.execSQL(DataBaseInfo.SQL_DROP_TABLE_QUERY)
            onCreate(p0)
     }
    }
}