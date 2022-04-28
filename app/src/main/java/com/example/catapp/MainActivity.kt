package com.example.catapp

import android.app.Activity
import android.content.Context
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import com.example.catapp.databinding.ActivityMainBinding
import com.example.catapp.repository.Repository
import kotlinx.coroutines.currentCoroutineContext
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import kotlin.coroutines.coroutineContext

class MainActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var biding:ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(biding.root)
        supportActionBar?.hide();

        biding.btnCatSearch.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id== R.id.btnCatSearch){
            val progressBar = biding.pbLoading
            progressBar.visibility=View.VISIBLE

            val repository= Repository()
            val viewModelFactory = MainViewModelFactory(repository)
            viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

            doAsync {
                viewModel.getImage()
                val imgCat = biding.imgCat //vai pegar a imagem imgCat
//                val newimg = HttpHelper().get("") //joga o link da api e chama o método
                uiThread {
                    val inputStream = viewModel.myResponse.value?.byteStream()
                    val bitmaping = BitmapFactory.decodeStream(inputStream)
                    progressBar.visibility = View.INVISIBLE
                    biding.imgCat.setImageBitmap(bitmaping)
//                    imgCat.setImageBitmap(newimg)
                }
            }
        }
    }
}