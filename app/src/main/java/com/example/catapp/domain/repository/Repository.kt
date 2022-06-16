package com.example.catapp.domain.repository

import androidx.annotation.WorkerThread
import com.example.catapp.data.api.RetrofitInstance
import com.example.catapp.data.database.daos.CatDao
import com.example.catapp.data.models.CatPhoto
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody

class Repository(private val catDao: CatDao?) {
    val allCats: Flow<List<CatPhoto>>? = catDao?.getCatsFromDB()

    @WorkerThread
    suspend fun insertInDatabase(cat: CatPhoto) {
        catDao?.insertCatInDB(cat)
    }

    suspend fun getFromApiImage(): ResponseBody {
        return RetrofitInstance.API.getImage()
    }
}
