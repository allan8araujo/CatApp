package com.example.catapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import org.jetbrains.anko.doAsync

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMostrarGato= findViewById<Button>(R.id.btnCatSearch)
        btnMostrarGato.setOnClickListener {
            doAsync {
                val imgCat=findViewById<ImageView>(R.id.imgCat)
                val newimg=HttpHelper().get("https://cataas.com/c")
                imgCat.setImageBitmap(newimg)  }
        }

    }
}