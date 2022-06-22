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
    private val mutableSelectedItem = MutableLiveData<CatPhoto>()
    val selectedItem: LiveData<CatPhoto> get() = mutableSelectedItem
    fun imageSelected(SelectedCat: CatPhoto) {
        mutableSelectedItem.value = SelectedCat
    }

    private val liveList: LiveData<MutableList<CatPhoto>>? = repository.allCats?.asLiveData()
    val allCats: LiveData<MutableList<CatPhoto>>? = liveList

    fun insert(cat: CatPhoto?) = viewModelScope.launch {
        repository.insertInDatabase(cat)
    }

    fun delete(cat: CatPhoto?) = viewModelScope.launch {
        repository.deleteInDatabase(cat)
    }
}
