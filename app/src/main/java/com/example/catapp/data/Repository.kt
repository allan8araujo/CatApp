package com.example.catapp.data

import androidx.annotation.WorkerThread
import com.example.abstractions.CatPhoto
import com.example.api.api.RetrofitInstance
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody

class Repository(private val catDao: com.example.database.daos.CatDao?) {
    val allCats: Flow<MutableList<CatPhoto>>? = catDao?.getCatsFromDB()

    @WorkerThread
    suspend fun insertInDatabase(cat: CatPhoto?) {
        catDao?.insertCatInDB(cat)
    }

    @WorkerThread
    suspend fun deleteInDatabase(cat: CatPhoto?) {
        catDao?.deleteCatInDB(cat)
    }

    suspend fun getFromApiImage(): ResponseBody {
        return RetrofitInstance.API.getImage()
    }
}
