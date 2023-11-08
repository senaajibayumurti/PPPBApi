package com.example.pertemuan11.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    fun getInstance(): ApiService {
        val mOkHttpInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        val mOkHttpClient = OkHttpClient
            .Builder().addInterceptor(mOkHttpInterceptor)
            .build()
        val builder = Retrofit.Builder()
            .baseUrl("https://dummy.restapiexample.com/api/v1/")
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return builder.create(ApiService::class.java)
    }
}