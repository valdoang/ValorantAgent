package com.valdoang.valorantagent.core.data.source.remote

import android.util.Log
import com.valdoang.valorantagent.core.data.source.remote.network.ApiResponse
import com.valdoang.valorantagent.core.data.source.remote.network.ApiService
import com.valdoang.valorantagent.core.data.source.remote.response.ValorantResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllValorant(): Flow<ApiResponse<List<ValorantResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.data
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}