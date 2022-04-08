package com.example.catapp

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMostrarGato = findViewById<Button>(R.id.btnCatSearch)
        var progressBar = findViewById<ProgressBar>(R.id.pbLoading)


        btnMostrarGato.setOnClickListener { //seta o evento btnCLick
            progressBar.visibility = View.VISIBLE

            doAsync {
                val imgCat = findViewById<ImageView>(R.id.imgCat) //vai pegar a imagem imgCat
                var newimg = HttpHelper().get("https://cataas.com/c") //joga o link da api e chama o método
                uiThread {
                    progressBar.visibility = View.INVISIBLE
                    imgCat.setImageBitmap(newimg)
                }
            }
        }

    }
}