package com.example.catapp.data

import androidx.annotation.WorkerThread
import androidx.paging.PagingSource
import com.example.abstractions.CatPhoto
import com.example.api.api.RetrofitInstance
import okhttp3.ResponseBody

class Repository(private val catDao: com.example.database.daos.CatDao?) {
    val allCats: PagingSource<Int, CatPhoto>? = catDao?.getCatsFromDB()

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
