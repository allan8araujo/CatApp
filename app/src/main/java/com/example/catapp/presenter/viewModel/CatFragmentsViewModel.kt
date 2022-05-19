package com.example.catapp.presenter.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catapp.presenter.view.adapters.CatPhoto

class CatFragmentsViewModel : ViewModel() {
    private val mutableSelectedItem = MutableLiveData<CatPhoto>()
    val selectedItem: LiveData<CatPhoto> get() = mutableSelectedItem

    fun imageSelected(SelectedCat: CatPhoto) {
        mutableSelectedItem.value = SelectedCat
    }
}
