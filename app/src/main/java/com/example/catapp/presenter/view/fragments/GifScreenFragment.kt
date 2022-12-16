package com.example.catapp.presenter.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.catapp.R
import com.example.catapp.presenter.viewModel.GifScreenViewModel
import com.example.catapp.presenter.viewModel.MainScreenViewModel

class GifScreenFragment : Fragment() {
    private val gifScreenViewModel: GifScreenViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        gifScreenViewModel.getGif()
        gifScreenViewModel.catResponseGif.observe(viewLifecycleOwner){

        }
        return inflater.inflate(R.layout.fragment_gif_screen, container, false)
    }
}
