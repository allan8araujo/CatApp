package com.example.catapp.data.api

import okhttp3.ResponseBody
import retrofit2.http.GET

interface CatApi {
    @GET("c")
    suspend fun getImage(): ResponseBody
}
