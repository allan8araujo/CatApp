package com.example.catapp.presenter.viewModel

import android.graphics.BitmapFactory
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.*
import androidx.paging.*
import com.bumptech.glide.Glide
import com.example.abstractions.CatPhoto
import com.example.catapp.data.Repository
import com.example.catapp.databinding.FragmentCatBinding
import com.example.catapp.databinding.FragmentHistoryBinding
import com.example.catapp.presenter.view.adapters.CatItemAdapter
import com.example.catapp.presenter.view.adapters.ProgressBarListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class CatViewModel(private val repository: Repository) : ViewModel() {
    val catResponse: MutableLiveData<ResponseBody?> = MutableLiveData()
    fun getImage() {
        viewModelScope.launch {
            try {
                val response: ResponseBody = repository.getFromApiImage()
                catResponse.value = response
            } catch (e: Exception) {
            }
        }
    }

    // Fullscreen vm
    private val mutableSelectedItem = MutableLiveData<CatPhoto>()
    val selectedItem: LiveData<CatPhoto> get() = mutableSelectedItem
    fun imageSelected(SelectedCat: CatPhoto) {
        mutableSelectedItem.value = SelectedCat
    }

    private val liveList: Flow<PagingData<CatPhoto>> =
        Pager(PagingConfig(pageSize = 3)) {
            repository.allCats!!
        }.flow.cachedIn(viewModelScope)
    val allCats: Flow<PagingData<CatPhoto>> = liveList

    fun insert(cat: CatPhoto?) = viewModelScope.launch {
        repository.insertInDatabase(cat)
    }

    fun delete(cat: CatPhoto?) = viewModelScope.launch {
        repository.deleteInDatabase(cat)
    }

    fun observeCatResponse(
        binding: FragmentCatBinding,
        progressBar: ProgressBar,
        responseBody: ResponseBody?,
    ) {
        val responseToBitmap =
            BitmapFactory.decodeStream(responseBody?.byteStream())

        Glide.with(binding.root.context)
            .load(responseToBitmap)
            .centerCrop()
            .listener(ProgressBarListener(progressBar, this))
            .into(binding.imgCat)
    }

    fun setCatList(
        listCatPhoto: PagingData<CatPhoto>,
        catListAdapter: CatItemAdapter,
        binding: FragmentHistoryBinding,
    ) {
        viewModelScope.launch {
            catListAdapter.submitData(listCatPhoto)
            if (catListAdapter.itemCount == 0) {
                binding.pbLoadingHistory.visibility = View.VISIBLE
            } else {
                binding.pbLoadingHistory.visibility = View.GONE
            }
        }
    }
}
