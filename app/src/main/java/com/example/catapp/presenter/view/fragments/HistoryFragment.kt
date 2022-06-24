package com.example.catapp.presenter.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.abstractions.CatPhoto
import com.example.catapp.R
import com.example.catapp.databinding.FragmentHistoryBinding
import com.example.catapp.presenter.view.adapters.CatItemAdapter
import com.example.catapp.presenter.viewModel.CatViewModel

class HistoryFragment : Fragment() {
    private val catFragmentsViewModel: CatViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val view = binding.root
        val catListAdapter = CatItemAdapter()
        catFragmentsViewModel.allCats?.observe(viewLifecycleOwner) { listCatPhoto ->
            catFragmentsViewModel.setCatList(listCatPhoto, catListAdapter, binding)
        }

        binding.catListRecycerview.adapter = catListAdapter
        catListAdapter.onClickListener = { imageId ->
            onClickCatList(imageId)
        }
        return view
    }

    private fun onClickCatList(cat: CatPhoto) {
        findNavController().navigate(R.id.to_fullScreenFragment)
        catFragmentsViewModel.imageSelected(cat)
    }
}
