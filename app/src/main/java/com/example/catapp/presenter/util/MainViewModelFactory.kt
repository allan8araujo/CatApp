package com.example.catapp.presenter.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.catapp.data.repository.Repository
import com.example.catapp.presenter.viewModel.GetCatViewModel

class MainViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GetCatViewModel(repository) as T
    }
}
