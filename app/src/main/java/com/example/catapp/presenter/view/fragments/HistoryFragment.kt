package com.example.catapp.presenter.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.catapp.R
import com.example.catapp.databinding.FragmentHistoryBinding
import com.example.catapp.presenter.view.adapters.CatItemAdapter
import com.example.catapp.presenter.view.adapters.CatPhoto
import com.example.catapp.presenter.viewModel.CatFragmentsViewModel
import com.example.catapp.presenter.util.CatImageList

class HistoryFragment : Fragment() {
    private val catFragmentsViewModel: CatFragmentsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val view = binding.root
        val catListAdapter = CatItemAdapter()

        binding.catListRecycerview.adapter = catListAdapter
        catListAdapter.submitList(CatImageList.listcats)
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
