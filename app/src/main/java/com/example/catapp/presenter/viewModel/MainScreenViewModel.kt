package com.example.catapp.presenter.viewModel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ProgressBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.example.abstractions.CatPhoto
import com.example.catapp.data.Repository
import com.example.catapp.databinding.FragmentCatBinding
import com.example.catapp.presenter.view.adapters.CatItemAdapter
import com.example.catapp.presenter.view.adapters.CatLoadStateAdapter
import com.example.catapp.presenter.view.adapters.ProgressBarListener
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream

class MainScreenViewModel(private val repository: Repository) : ViewModel() {
    val catResponse: MutableLiveData<ResponseBody?> = MutableLiveData()
    var catResponseState: MutableLiveData<Boolean?> = MutableLiveData()

    fun getImage() {
        viewModelScope.launch {
            catResponseState.value = null
            try {
                val response: ResponseBody = repository.getFromApiImage()
                catResponse.value = response
                catResponseState.value = true
            } catch (e: Exception) {
                catResponseState.value = false
            }
        }
    }

    // Fullscreen vm
    private val mutableSelectedItem = MutableLiveData<CatPhoto>()
    val itemSelected: LiveData<CatPhoto> get() = mutableSelectedItem
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
        responseBody: ResponseBody?,
    ) {
        val responseToBitmap =
            BitmapFactory.decodeStream(responseBody?.byteStream())

        Glide.with(binding.root.context)
            .load(responseToBitmap)
            .centerCrop()
            .listener(ProgressBarListener(this))
            .into(binding.imgCat)
    }

    fun settingRecyclerView(
        catListRecycerview: RecyclerView,
        catListAdapter: CatItemAdapter,
    ) {
        catListRecycerview.adapter = catListAdapter.withLoadStateFooter(
            CatLoadStateAdapter()
        )
    }

    fun settingSwipeRefresh(swiperefresh: SwipeRefreshLayout, catListAdapter: CatItemAdapter) {
        swiperefresh.setOnRefreshListener {
            catListAdapter.refresh()
            swiperefresh.isRefreshing = false
        }
    }

    fun submitDataOnAdapter(
        catFragmentsViewModel: MainScreenViewModel,
        catListAdapter: CatItemAdapter
    ) {
        viewModelScope.launch {
            catFragmentsViewModel.getDataFromRemote().collectLatest { pagingData ->
                catListAdapter.submitData(pagingData)
            }
        }
    }

    fun getCatUri(bitmap: Bitmap, imageFolder: File): File {
        val file = File(imageFolder, "shared_image.png")
        val stream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, stream)
        stream.flush()
        stream.close()
        return file
    }
}
