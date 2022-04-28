package com.example.catapp.repository

import com.example.catapp.api.RetrofitInstance
import okhttp3.ResponseBody

class Repository {
    suspend fun getImage():ResponseBody{
        return RetrofitInstance.api.getImage()
    }
}