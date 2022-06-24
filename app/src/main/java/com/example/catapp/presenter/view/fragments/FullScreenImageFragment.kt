package com.example.catapp.presenter.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.catapp.R
import com.example.catapp.databinding.FragmentFullScreenImageBinding
import com.example.catapp.presenter.viewModel.CatViewModel

class FullScreenImageFragment : Fragment(R.layout.fragment_full_screen_image) {
    private val catFragmentsViewModel: CatViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentFullScreenImageBinding.inflate(inflater, container, false)
        val view = binding.root

        catFragmentsViewModel.selectedItem.value.let { catPhoto ->
            binding.deleteBtn.setOnClickListener {
                findNavController().navigate(R.id.back_to_historyFragment)
                catFragmentsViewModel.delete(catPhoto)
            }
            binding.imageFullscreen.setImageBitmap(catPhoto?.image)
        }

        binding.backToList.setOnClickListener {
            findNavController().navigate(R.id.back_to_historyFragment)
        }

        return view
    }
}
