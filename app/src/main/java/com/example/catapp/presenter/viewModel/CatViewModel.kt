package com.example.catapp.presenter.viewModel

import androidx.lifecycle.*
import com.example.modelsdto.models.CatPhoto
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

    val allCats: LiveData<List<CatPhoto>>? = repository.allCats?.asLiveData()
    fun insert(cat: CatPhoto) = viewModelScope.launch {
        repository.insertInDatabase(cat)
    }
}
