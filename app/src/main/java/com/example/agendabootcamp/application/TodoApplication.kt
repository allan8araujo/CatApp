package com.example.agendabootcamp.application

import android.app.Application
import com.example.agendabootcamp.DataBaseOperations

class TodoApplication:Application() {
    var helperDb:DataBaseOperations?=null
    private set

    companion object {
        lateinit var instace:TodoApplication
    }

    override fun onCreate() {
        super.onCreate()
        instace=this
        helperDb=DataBaseOperations(this)
    }
}