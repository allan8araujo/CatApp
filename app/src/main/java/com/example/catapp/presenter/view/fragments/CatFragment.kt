package com.example.catapp.presenter.view.fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.catapp.R
import com.example.catapp.data.repository.Repository
import com.example.catapp.databinding.FragmentCatBinding
import com.example.catapp.presenter.util.MainViewModelFactory
import com.example.catapp.presenter.view.adapters.ProgressBarListener
import com.example.catapp.presenter.viewModel.GetCatViewModel

class CatFragment : Fragment(R.layout.fragment_cat) {

    private val repository = Repository()
    private val viewModelFactory = MainViewModelFactory(repository)
    private val viewModelGet: GetCatViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(GetCatViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentCatBinding.inflate(inflater, container, false)
        val progressBar = binding.pbLoading
        val view = binding.root

        progressBar.visibility = View.VISIBLE
        myResponseObserve(binding, progressBar)
        viewModelGet.getImage()

        binding.buttonCatSearch.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            viewModelGet.getImage()
        }
        return view
    }

    private fun myResponseObserve(binding: FragmentCatBinding, progressBar: ProgressBar) {

        viewModelGet.myResponse.observe(viewLifecycleOwner) {
            val responseToBitmap =
                BitmapFactory.decodeStream(viewModelGet.myResponse.value?.byteStream())

            Glide.with(binding.root.context)
                .load(responseToBitmap)
                .centerCrop()
                .listener(ProgressBarListener(progressBar))
                .into(binding.imgCat)
        }
    }
}
