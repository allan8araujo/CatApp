package com.example.catapp.api

import okhttp3.ResponseBody
import retrofit2.http.GET

interface catApi{
    @GET("c")
    suspend fun getImage(): ResponseBody

}