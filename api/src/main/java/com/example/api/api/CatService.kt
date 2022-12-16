package com.example.api.api

import okhttp3.ResponseBody
import retrofit2.http.GET

interface CatService {
    @GET("c")
    suspend fun getImage(): ResponseBody

    @GET("cat/gif")
    suspend fun getGif(): ResponseBody
}
