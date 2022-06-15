package com.example.catapp.presenter.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.catapp.data.repository.DaoRepository

import com.example.catapp.presenter.viewModel.CatViewModel

class MainViewModelFactory(val repository: DaoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CatViewModel(repository) as T
    }
}