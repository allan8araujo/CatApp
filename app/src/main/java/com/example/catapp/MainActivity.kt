package com.example.catapp

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.catapp.databinding.ActivityMainBinding
import com.example.catapp.repository.Repository
import com.example.catapp.ui.fragments.CatFragment
import com.example.catapp.ui.fragments.HistoryFragment
import com.example.catapp.viewModel.MainViewModel
import com.example.catapp.viewModel.MainViewModelFactory

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val catFragment = CatFragment()
    private val historyFragment = HistoryFragment()

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
        biding.buttonCatSearch.setOnClickListener(this)
        biding.buttonArrowHistory.setOnClickListener(this)

        myResponseObserve()
        progressBar.visibility = View.VISIBLE
        viewModel.getImage()
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_cat_search) {
            progressBar.visibility = View.VISIBLE
            viewModel.getImage()
        } else if (view.id == R.id.button_arrow_history) {

            val a: FragmentTransaction = supportFragmentManager.beginTransaction()
            val b = Fragment()
            a.replace(R.id.container, b)
            a.commit()
        }
    }

    private fun myResponseObserve() {
        viewModel.myResponse.observe(this) {
            Glide.with(biding.root.context)
                .load(BitmapFactory.decodeStream(viewModel.myResponse.value?.byteStream()))
                .listener(ProgressBarListener(progressBar))
                .centerCrop()
                .into(biding.imgCat)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container,fragment)
            transaction.commit()
        }
    }
}
