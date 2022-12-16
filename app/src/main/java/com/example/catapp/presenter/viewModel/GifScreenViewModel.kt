package com.example.catapp.presenter.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapp.data.Repository
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class GifScreenViewModel(private val repository: Repository) : ViewModel() {
    val catResponseGif: MutableLiveData<ResponseBody?> = MutableLiveData()
    var catResponseGifState: MutableLiveData<Boolean?> = MutableLiveData()

    fun getGif() {
        viewModelScope.launch {
            catResponseGifState.value = null
            try {
                val response: ResponseBody = repository.getGif()
                catResponseGif.value = response
                catResponseGifState.value = true
            } catch (e: Exception) {
                catResponseGifState.value = false
            }
        }
    }
}