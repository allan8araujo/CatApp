package com.example.catapp.presenter.view.fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.example.catapp.data.Repository
import com.example.catapp.databinding.FragmentGifScreenBinding
import com.example.catapp.presenter.util.GifViewModelFactory
import com.example.catapp.presenter.viewModel.GifScreenViewModel
import com.example.database.CatDataBaseInstance

class GifScreenFragment : Fragment() {

    private val database by lazy { CatDataBaseInstance.CatRoomDatabase.getDataBase(requireContext()) }
    private val gifScreenViewModel: GifScreenViewModel by activityViewModels {
        GifViewModelFactory(Repository(database.appDao()))
    }
    lateinit var binding: FragmentGifScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGifScreenBinding.inflate(inflater, container, false)
        val view = binding.root
        gifScreenViewModel.getGif()

        gifScreenViewModel.catResponseGif.observe(viewLifecycleOwner) { responseBody ->
            val responseByteArray = responseBody?.byteStream()?.readBytes()

            Glide.with(binding.root.context)
                .load(responseByteArray)
                .centerCrop()
//                .listener(ProgressBarListener(this))
                .into(binding.gifCat)
        }

        binding.buttonCatSearch.setOnClickListener {
            gifScreenViewModel.getGif()
        }

        gifScreenViewModel.catResponseGifState.observe(viewLifecycleOwner) {
            when (it) {
                true -> {
                    binding.gifCat.isVisible = true
                    binding.pbLoading.isVisible = false
                }
                false -> {
                    binding.gifCat.isVisible = false
                    binding.pbLoading.isVisible = true
                }
                null -> {
                    binding.gifCat.isVisible = false
                    binding.pbLoading.isVisible = true
                }
            }
        }

        return view
    }
}
