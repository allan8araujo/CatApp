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
import com.example.abstractions.CatPhoto
import com.example.catapp.R
import com.example.catapp.databinding.FragmentHistoryBinding
import com.example.catapp.presenter.view.adapters.CatItemAdapter
import com.example.catapp.presenter.view.adapters.CatLoadStateAdapter
import com.example.catapp.presenter.viewModel.CatViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryFragment : Fragment() {
    private val catFragmentsViewModel: CatViewModel by activityViewModels()
    lateinit var catListAdapter: CatItemAdapter
    lateinit var catListRecycerview: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val view = binding.root
        catListAdapter = CatItemAdapter(binding)

        catFragmentsViewModel.allCats.observe(viewLifecycleOwner) { pagingData ->
            lifecycleScope.launch(Dispatchers.IO) {
                catListAdapter.submitData(pagingData)
            }
        }
        catListRecycerview = binding.catListRecycerview
        catListRecycerview.adapter = catListAdapter.withLoadStateFooter(
            CatLoadStateAdapter()
        )
        binding.swiperefresh.setOnRefreshListener {
            catListAdapter.refresh()
            binding.swiperefresh.isRefreshing = false
            catListRecycerview.smoothScrollToPosition(0)
        }
//        binding.catListRecycerview.adapter = catListAdapter.withLoadStateHeader(
//            CatLoadStateAdapter()
//        ) implementar posteriomrnete o load ao deslizar pra cima

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
