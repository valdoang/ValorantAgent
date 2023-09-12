package com.valdoang.valorantagent.core.data.source.remote.network

import com.valdoang.valorantagent.core.data.source.remote.response.ListValorantResponse
import retrofit2.http.GET

interface ApiService {

    @GET("agents")
    suspend fun getList(): ListValorantResponse

}