package com.example.catapp.presenter.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.catapp.R
import com.example.catapp.presenter.viewModel.GifScreenViewModel

class GifScreenFragment : Fragment() {

    companion object {
        fun newInstance() = GifScreenFragment()
    }

    private lateinit var viewModel: GifScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewModel = ViewModelProvider(this).get(GifScreenViewModel::class.java)
        return inflater.inflate(R.layout.fragment_gif_screen, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }
}
