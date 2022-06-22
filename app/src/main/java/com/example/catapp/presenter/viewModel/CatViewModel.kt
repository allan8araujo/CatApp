package com.example.catapp.presenter.viewModel

import androidx.lifecycle.*
import com.example.abstractions.CatPhoto
import com.example.catapp.data.Repository
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class CatViewModel(private val repository: Repository) : ViewModel() {
    val myResponse: MutableLiveData<ResponseBody> = MutableLiveData()
    fun getImage() {
        viewModelScope.launch {
            val response: ResponseBody = repository.getFromApiImage()
            myResponse.value = response
        }
    }

    // Fullscreen vm
    private val mutableSelectedItem = MutableLiveData<com.example.abstractions.CatPhoto>()
    val selectedItem: LiveData<com.example.abstractions.CatPhoto> get() = mutableSelectedItem
    fun imageSelected(SelectedCat: com.example.abstractions.CatPhoto) {
        mutableSelectedItem.value = SelectedCat
    }

    val allCats: LiveData<List<com.example.abstractions.CatPhoto>>? = repository.allCats?.asLiveData()
    fun insert(cat: com.example.abstractions.CatPhoto) = viewModelScope.launch {
        repository.insertInDatabase(cat)
    }
}
