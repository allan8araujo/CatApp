package com.example.api.api

import okhttp3.ResponseBody
import retrofit2.http.GET

interface CatApi {
    @GET("c")
    suspend fun getImage(): ResponseBody
}