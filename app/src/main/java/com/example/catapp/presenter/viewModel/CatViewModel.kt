package com.example.catapp.presenter.viewModel

import android.graphics.BitmapFactory
import android.widget.ProgressBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bumptech.glide.Glide
import com.example.abstractions.CatPhoto
import com.example.catapp.data.Repository
import com.example.catapp.databinding.FragmentCatBinding
import com.example.catapp.presenter.view.adapters.ProgressBarListener
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

    private var allCats: Flow<PagingData<CatPhoto>>? = null

    fun getDataFromRemote(): Flow<PagingData<CatPhoto>> {
        val newResult =
            repository.getAllCats
                .cachedIn(viewModelScope)
        allCats = newResult
        return newResult
    }

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
}
