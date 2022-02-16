package com.example.agendabootcamp.application

import android.app.Application
import com.example.agendabootcamp.DataBaseOperations

class TodoApplication:Application() {
    var helperDb:DataBaseOperations?=null
    private set
    override fun onCreate() {
        super.onCreate()
        helperDb=DataBaseOperations(this)
    }
}