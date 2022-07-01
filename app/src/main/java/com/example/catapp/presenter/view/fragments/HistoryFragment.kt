package com.example.catapp.presenter.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.abstractions.CatPhoto
import com.example.catapp.R
import com.example.catapp.databinding.FragmentHistoryBinding
import com.example.catapp.presenter.view.adapters.CatItemAdapter
import com.example.catapp.presenter.view.adapters.CatLoadStateAdapter
import com.example.catapp.presenter.viewModel.CatViewModel
import kotlinx.coroutines.launch

class HistoryFragment : Fragment() {
    private val catFragmentsViewModel: CatViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val view = binding.root
        val catListAdapter = CatItemAdapter(binding)

        lifecycleScope.launch {
            catFragmentsViewModel.allCats.collect { pagingData ->
                catFragmentsViewModel.setCatList(pagingData, catListAdapter)
            }
        }

        binding.catListRecycerview.adapter = catListAdapter.withLoadStateFooter(
            CatLoadStateAdapter()
        )

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
