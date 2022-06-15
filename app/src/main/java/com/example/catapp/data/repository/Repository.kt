package com.example.catapp.data.repository

import androidx.annotation.WorkerThread
import com.example.catapp.data.Daos.CatDao
import com.example.catapp.data.api.RetrofitInstance
import com.example.catapp.data.models.CatPhoto
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody

class DaoRepository(private val catDao: CatDao?) {
    val allCats: Flow<List<CatPhoto>>? = catDao?.getCatsFromDB()

    @WorkerThread
    suspend fun insert(cat: CatPhoto) {
        catDao?.insertCat(cat)
    }

    suspend fun getImage(): ResponseBody {
        return RetrofitInstance.API.getImage()
    }
}
