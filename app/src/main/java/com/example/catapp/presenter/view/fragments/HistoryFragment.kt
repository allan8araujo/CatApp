package com.example.catapp.presenter.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.example.abstractions.CatPhoto
import com.example.catapp.R
import com.example.catapp.databinding.FragmentHistoryBinding
import com.example.catapp.presenter.view.adapters.CatItemAdapter
import com.example.catapp.presenter.view.adapters.CatLoadStateAdapter
import com.example.catapp.presenter.viewModel.CatViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HistoryFragment : Fragment() {
    private val catFragmentsViewModel: CatViewModel by activityViewModels()
    private lateinit var catListAdapter: CatItemAdapter
    private lateinit var catListRecycerview: RecyclerView
    private lateinit var swiperefresh: SwipeRefreshLayout
    private lateinit var refreshListener: OnRefreshListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val view = binding.root
        catListAdapter = CatItemAdapter(binding)


        catListRecycerview = binding.catListRecycerview
        catListRecycerview.adapter = catListAdapter.withLoadStateFooter(
            CatLoadStateAdapter()
        )

        swiperefresh = binding.swiperefresh
        refreshListener = OnRefreshListener {
            catListAdapter.refresh()
            swiperefresh.isRefreshing = false
        }
        swiperefresh.setOnRefreshListener(refreshListener)

        catListAdapter.onClickListener = { imageId ->
            onClickCatList(imageId)
        }

        lifecycleScope.launch {
            catFragmentsViewModel.getDataFromRemote().collectLatest { pagingData ->
                catListAdapter.submitData(lifecycle, pagingData)
            }
        }
        return view
    }

    private fun onClickCatList(cat: CatPhoto) {
        findNavController().navigate(R.id.to_fullScreenFragment)
        catFragmentsViewModel.imageSelected(cat)
    }
}
