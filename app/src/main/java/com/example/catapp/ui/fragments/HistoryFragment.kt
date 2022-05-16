package com.example.catapp.ui.fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.catapp.ProgressBarListener
import com.example.catapp.R
import com.example.catapp.databinding.FragmentHistory2Binding
import com.example.catapp.repository.Repository
import com.example.catapp.viewModel.MainViewModel
import com.example.catapp.viewModel.MainViewModelFactory

class HistoryFragment : Fragment(R.layout.fragment_history2), View.OnClickListener {

    private val repository = Repository()
    private val viewModelFactory = MainViewModelFactory(repository)
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(MainViewModel::class.java)
    }

    private lateinit var binding: FragmentHistory2Binding

    override

    fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding = FragmentHistory2Binding.inflate(inflater, container, false)
        val view = binding.root
        val progressBar = binding.pbLoading
        progressBar.visibility = View.INVISIBLE
        binding.buttonCatSearch.setOnClickListener(this)
        //binding..setOnClickListener(this)

        viewModel.getImage()
        fun myResponseObserve() {
            viewModel.myResponse.observe(viewLifecycleOwner) {
                Glide.with(binding.root.context)
                    .load(BitmapFactory.decodeStream(viewModel.myResponse.value?.byteStream()))
                    .listener(ProgressBarListener(progressBar))
                    .centerCrop()
                    .into(binding.imgCat)
            }
        }

        myResponseObserve()
        progressBar.visibility = View.VISIBLE

        binding.buttonCatSearch.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            viewModel.getImage()
        }
//        binding.buttonArrowHistory.setOnClickListener {
////            val a: FragmentTransaction = supportFragmentManager.beginTransaction()
////            val b = Fragment()
////            a.replace(R.id.container, b)
////            a.commit()
//        }

        fun replaceFragment(fragment: Fragment) {
            if (fragment != null) {
//            val transaction = supportFragmentManager.beginTransaction()
//            transaction.replace(R.id.fragment_container, fragment)
//            transaction.commit()
            }
        }

        return view
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}
