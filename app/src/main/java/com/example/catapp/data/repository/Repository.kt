package com.example.catapp.data.repository

import com.example.catapp.data.api.RetrofitInstance
import okhttp3.ResponseBody

class Repository {
    suspend fun getImage(): ResponseBody {
        return RetrofitInstance.API.getImage()
    }
}
