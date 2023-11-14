package com.example.pppbapi.model

import com.google.gson.annotations.SerializedName

data class AgentData(
    @SerializedName("uuid") val uuid:String,
    @SerializedName("displayName") val agentName:String,
    @SerializedName("description") val agentDesc:String,
    @SerializedName("displayIcon") val agentIcon:String,
    @SerializedName("fullPortrait") val agentImage:String,
    @SerializedName("background") val agentBackground:String,
    @SerializedName("backgroundGradientColors") val agentColorGradient:List<String>,
    @SerializedName("role") val agentRole:Role,
    @SerializedName("abilities") val agentAbilities:List<Abilities>
)