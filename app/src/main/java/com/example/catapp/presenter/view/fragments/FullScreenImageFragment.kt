package com.example.catapp.presenter.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.catapp.R
import com.example.catapp.databinding.FragmentFullScreenImageBinding
import com.example.catapp.presenter.viewModel.CatFragmentsViewModel

class FullScreenImageFragment : Fragment(R.layout.fragment_full_screen_image) {
    private val catFragmentsViewModel: CatFragmentsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding = FragmentFullScreenImageBinding.inflate(inflater, container, false)
        binding.backToList.setOnClickListener {
            findNavController().navigate(R.id.back_to_historyFragment)
        }
        binding.imageFullscreen.setImageBitmap(catFragmentsViewModel.selectedItem.value?.image)
        val view = binding.root
        return view
    }
}
