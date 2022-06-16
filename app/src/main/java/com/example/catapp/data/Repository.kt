package com.example.catapp.data

import androidx.annotation.WorkerThread
import com.example.api.api.RetrofitInstance
import com.example.modelsdto.models.CatPhoto
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody

class Repository(private val catDao: com.example.database.daos.CatDao?) {
    val allCats: Flow<List<CatPhoto>>? = catDao?.getCatsFromDB()

    @WorkerThread
    suspend fun insertInDatabase(cat: CatPhoto) {
        catDao?.insertCatInDB(cat)
    }

    suspend fun getFromApiImage(): ResponseBody {
        return RetrofitInstance.API.getImage()
    }
}