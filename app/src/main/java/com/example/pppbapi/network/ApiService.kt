package com.example.pppbapi.network

import com.example.pppbapi.model.Agents
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("agents")
    fun getAllAgents(): Call<Agents>
}