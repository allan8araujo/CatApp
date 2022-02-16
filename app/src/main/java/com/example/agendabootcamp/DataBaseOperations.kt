package com.example.agendabootcamp

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

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

    fun salvarTodo(todo:Todo){
        val db=writableDatabase?:return
        db.execSQL(DataBaseInfo.SQL_INSERT_ON_TABLE)
        db.close()
    }

    fun buscarTodos(busca:String): List<Todo>{
        val db= readableDatabase?:return emptyList()
        var lista= mutableListOf<Todo>()
        var cursor:Cursor=db.rawQuery(DataBaseInfo.SQL_SELECT_ALL, arrayOf())?:return emptyList()
        if (cursor==null){
            db.close()
            return emptyList()
        }
        while (cursor.moveToNext()){
        var ParaFazer=Todo(
            cursor.getString(cursor.getColumnIndexOrThrow(DataBaseInfo.TableInfo.COLUMN_NAME))
            ,false)
        lista.add(ParaFazer)
        }
        db.close()
        return lista
    }

}