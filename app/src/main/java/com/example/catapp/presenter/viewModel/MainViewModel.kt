package com.example.catapp.presenter.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapp.data.repository.Repository
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class MainViewModel(private val repository: Repository) : ViewModel() {
    val myResponse: MutableLiveData<ResponseBody> = MutableLiveData()
    fun getImage() {
        viewModelScope.launch {
            val response: ResponseBody = repository.getImage()
            myResponse.value = response
        }
    }
}
