package com.valdoang.valorantagent.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListValorantResponse (

    @field:SerializedName("status")
    val status: Int,

    @field:SerializedName("data")
    val data: List<ValorantResponse>
)