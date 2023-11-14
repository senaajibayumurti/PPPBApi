package com.example.pppbapi.model

import com.google.gson.annotations.SerializedName

data class Abilities(
    @SerializedName("displayName") val abilityName: String,
    @SerializedName("description") val abilityDesc: String,
    @SerializedName("displayIcon") val abilityIcon: String,
)
