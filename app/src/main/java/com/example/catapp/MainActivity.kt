package com.example.catapp

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
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
//            val progressBar = biding.pbLoading
//            val repository= Repository()
//            val viewModelFactory = MainViewModelFactory(repository)
//            viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

//            viewModel.getImage()
//            biding.imgCat.setImageBitmap(null)
            Glide.with(biding.root.context)
                .load("https://cataas.com/cat")
                .centerCrop()
                .into(biding.imgCat)
//            doAsync {
//                viewModel.getImage()
//
//                uiThread {
//
//                    val inputStream = viewModel.myResponse.value?.byteStream()
//                    val bitmaping = BitmapFactory.decodeStream(inputStream)
//                    progressBar.visibility = View.INVISIBLE
//                    biding.imgCat.setImageBitmap(bitmaping)
//                }
//            }
        }
    }
}