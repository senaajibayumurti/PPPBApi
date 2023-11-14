package com.example.pppbapi.model

import com.google.gson.annotations.SerializedName

data class Role(
    @SerializedName("uuid") val roleID: String,
    @SerializedName("displayName") val roleName: String,
    @SerializedName("description") val roleDesc: String,
    @SerializedName("displayIcon") val roleIcon: String
)
