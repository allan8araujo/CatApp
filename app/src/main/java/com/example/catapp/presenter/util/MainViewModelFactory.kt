package com.example.catapp.presenter.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.catapp.data.Repository
import com.example.catapp.presenter.viewModel.GifScreenViewModel
import com.example.catapp.presenter.viewModel.MainScreenViewModel

class MainViewModelFactory(val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainScreenViewModel(repository) as T
    }
}

class GifViewModelFactory(val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GifScreenViewModel(repository) as T
    }
}
