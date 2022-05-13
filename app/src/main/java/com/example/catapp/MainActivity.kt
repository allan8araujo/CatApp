package com.example.catapp

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.catapp.databinding.ActivityMainBinding
import com.example.catapp.repository.Repository

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val biding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    val progressBar by lazy { biding.pbLoading }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(biding.root)
        progressBar.visibility = View.INVISIBLE
        biding.btnCatSearch.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == biding.btnCatSearch.id) {
            val repository = Repository()
            val viewModelFactory = MainViewModelFactory(repository)
            viewModel =
                ViewModelProvider(this, viewModelFactory)
                    .get(MainViewModel::class.java)
            viewModel.getImage()

            Glide.with(biding.root.context)
                .load(BitmapFactory.decodeStream(viewModel.myResponse.value?.byteStream()))
              //  .placeholder(progressBar)
                .listener(LoadProgressBar(progressBar))
                .centerCrop()
                .into(biding.imgCat)
        }
    }
}
