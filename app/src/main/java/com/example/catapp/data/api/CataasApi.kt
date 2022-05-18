package com.example.catapp.data.api

import com.example.catapp.presenter.adapters.CatPhoto
import okhttp3.ResponseBody
import retrofit2.http.GET

interface CatApi {
    @GET("c")
    suspend fun getImage(): ResponseBody
}
