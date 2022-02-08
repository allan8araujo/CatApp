package com.example.catapp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import okhttp3.*

class HttpHelper //classe responsável pela funget
{
    fun get(URL:String):Bitmap? {
        val client=OkHttpClient()//criando cliente que vai disparar a requisição
        val request=Request.Builder().url(URL).build() //criando a requisição GET na URL
        val response=client.newCall(request).execute() // pegando o componente da okHttpClient e
        val inputStream =response.body?.byteStream()
        var bitmaping=BitmapFactory.decodeStream(inputStream)
        return bitmaping //efetivamente retorna o bitmap
    }
}