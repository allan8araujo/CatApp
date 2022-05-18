package com.example.catapp.presenter.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.catapp.databinding.FragmentHistoryBinding
import com.example.catapp.presenter.adapters.CatItemAdapter
import com.example.catapp.presenter.viewModel.CatImageList

class HistoryFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val view = binding.root
        val catListAdapter = CatItemAdapter()
        binding.catListRecycerview.adapter = catListAdapter

        catListAdapter.submitList(CatImageList.listcats)

        return view
    }
}
