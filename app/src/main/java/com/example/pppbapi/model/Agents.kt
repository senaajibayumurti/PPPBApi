package com.example.pppbapi.model

import com.google.gson.annotations.SerializedName

data class Agents (
    @SerializedName("data")
    val data: List<AgentData>
    )