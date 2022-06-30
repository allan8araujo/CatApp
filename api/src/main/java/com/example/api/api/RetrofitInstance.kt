package com.example.api.api

import com.example.api.api.constants.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit
            .Builder()
            .baseUrl(Constants.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val API: CatService by lazy {
        retrofit.create(CatService::class.java)
    }
}
