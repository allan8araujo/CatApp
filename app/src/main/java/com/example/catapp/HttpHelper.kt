package com.example.catapp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import okhttp3.*

class HttpHelper //classe responsável pela funget
{
    fun get(URL: String): Bitmap? {
        val client = OkHttpClient()//criando cliente que vai disparar a requisição
        val request = Request.Builder().url(URL).build() //criando a requisição GET na URL
        val response = client.newCall(request)
            .execute() // pegando resposta do servidor na chamada newCall(request)
        val inputStream = response.body?.byteStream() //pegando o body da resposta em bytestream
        val bitmaping = BitmapFactory.decodeStream(inputStream) //decodando a stream do inputStream
        return bitmaping //efetivamente retorna o bitmap derivado do request
    }
}