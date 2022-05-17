package com.example.catapp.presenter.view.fragments

import android.graphics.BitmapFactory
import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.catapp.R
import com.example.catapp.data.repository.Repository
import com.example.catapp.databinding.FragmentCatBinding
import com.example.catapp.presenter.adapters.ProgressBarListener
import com.example.catapp.presenter.view.adapter.CatPhoto
import com.example.catapp.presenter.viewModel.CatImageList
import com.example.catapp.presenter.viewModel.MainViewModel
import com.example.catapp.presenter.viewModel.MainViewModelFactory

class CatFragment : Fragment(R.layout.fragment_cat) {

    private val repository = Repository()
    private val viewModelFactory = MainViewModelFactory(repository)
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentCatBinding.inflate(inflater, container, false)
        val view = binding.root
        val progressBar = binding.pbLoading
        progressBar.visibility = View.INVISIBLE
        viewModel.getImage()
        progressBar.visibility = View.VISIBLE
        myResponseObserve(binding, progressBar)

        binding.buttonCatSearch.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            viewModel.getImage()
        }
        return view
    }

    private fun myResponseObserve(binding: FragmentCatBinding, progressBar: ProgressBar) {

        viewModel.myResponse.observe(viewLifecycleOwner) {
            val responseToBitmap =
                BitmapFactory.decodeStream(viewModel.myResponse.value?.byteStream())
            Glide.with(binding.root.context)
                .load(responseToBitmap)
                .centerCrop()
                .listener(ProgressBarListener(progressBar))
                .into(binding.imgCat)
            val image=ImageView(binding.root.context)
            image.setImageBitmap(responseToBitmap)
            CatImageList.listcats.add(CatPhoto(image))
        }
    }
}
