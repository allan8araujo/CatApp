package com.example.catapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun onTap(view: View) {
        val btnChecarGato= findViewById(R.id.btnCatSearch) as Button
        btnChecarGato.setOnClickListener {
            Toast.makeText(this, "Eu acho que eu vi um gatinho", Toast.LENGTH_SHORT).show()
        }
    }


}