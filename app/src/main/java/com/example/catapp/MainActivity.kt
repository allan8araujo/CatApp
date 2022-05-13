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
    private val progressBar by lazy { biding.pbLoading }
    private val repository = Repository()
    private val viewModelFactory = MainViewModelFactory(repository)
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(biding.root)
        progressBar.visibility = View.INVISIBLE
        biding.btnCatSearch.setOnClickListener(this)

        viewModel.myResponse.observe(this) { response ->
            Glide.with(biding.root.context)
                .load(BitmapFactory.decodeStream(viewModel.myResponse.value?.byteStream()))
                .listener(LoadProgressBar(progressBar))
                .centerCrop()
                .into(biding.imgCat)
        }
    }

    override fun onClick(view: View) {
        if (view.id == biding.btnCatSearch.id) {
            progressBar.visibility = View.VISIBLE
            viewModel.getImage()
        }
    }
}
