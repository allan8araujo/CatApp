package com.example.agendabootcamp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.agendabootcamp.application.TodoApplication
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_todo.*

class MainActivity : AppCompatActivity() {
    private var adapter:TodoAdapter?=null
    private lateinit var todoAdapter: TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter= TodoAdapter(mutableListOf())

        rvTodoItems.adapter=todoAdapter
        rvTodoItems.layoutManager=LinearLayoutManager(this)

        btnAddTodoList.setOnClickListener {
            TodoApplication.instace.helperDb?.salvarTodo(Todo("0",false))
            val todoTitle=etTodoTitle.text.toString()
            if(todoTitle.isNotEmpty()){
                val todo= Todo(todoTitle,false)
                todoAdapter.addTodo(todo)
                etTodoTitle.text.clear()
            }
        }

        btnRefresh.setOnClickListener {
            var listaFiltrada:List<Todo>
            try {
                listaFiltrada=TodoApplication.instace.helperDb?.buscarTodos("")?: mutableListOf()
            }catch (ex:Exception){
                ex.printStackTrace()
            }

        }

        btnDeleteTodoList.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }

        }
    }
