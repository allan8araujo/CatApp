package com.example.catapp.presenter.view.fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.catapp.databinding.FragmentCatBinding
import com.example.catapp.presenter.view.adapters.ProgressBarListener
import com.example.catapp.presenter.viewModel.CatViewModel

class CatFragment : Fragment() {
    private val catViewModel: CatViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentCatBinding.inflate(inflater, container, false)
        val view = binding.root
        val progressBar = binding.pbLoading
        progressBar.visibility = View.VISIBLE
        catViewModel.getImage()
        myResponseObserve(binding, progressBar)

        binding.buttonCatSearch.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            catViewModel.getImage()
        }
        return view
    }

    private fun myResponseObserve(binding: FragmentCatBinding, progressBar: ProgressBar) {
        try {
            catViewModel.myResponse.observe(viewLifecycleOwner) { responseBody ->
                val responseToBitmap =
                    BitmapFactory.decodeStream(responseBody.byteStream())

                Glide.with(binding.root.context)
                    .load(responseToBitmap)
                    .centerCrop()
                    .listener(ProgressBarListener(progressBar, catViewModel))
                    .into(binding.imgCat)
            }
        } catch (e: Exception) {
            Log.i("@@@@@@", e.message.toString())
        }
    }
}
