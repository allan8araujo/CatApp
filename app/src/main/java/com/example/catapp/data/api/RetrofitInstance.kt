package com.example.catapp.data.api

import com.example.catapp.domain.Constants
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
    val API: CatApi by lazy {
        retrofit.create(CatApi::class.java)
    }
}
