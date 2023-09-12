package com.valdoang.valorantagent.core.data

import com.valdoang.valorantagent.core.data.source.local.LocalDataSource
import com.valdoang.valorantagent.core.data.source.remote.RemoteDataSource
import com.valdoang.valorantagent.core.data.source.remote.network.ApiResponse
import com.valdoang.valorantagent.core.data.source.remote.response.ValorantResponse
import com.valdoang.valorantagent.core.domain.model.Valorant
import com.valdoang.valorantagent.core.domain.repository.IValorantRepository
import com.valdoang.valorantagent.core.utils.AppExecutors
import com.valdoang.valorantagent.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ValorantRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IValorantRepository {
    override fun getAllAgent(): Flow<Resource<List<Valorant>>> =
        object : NetworkBoundResource<List<Valorant>, List<ValorantResponse>>() {
            override fun loadFromDB(): Flow<List<Valorant>> {
                return localDataSource.getAllAgent().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Valorant>?): Boolean =
                data == null || data.isEmpty()
                // ganti dengan true jika ingin selalu mengambil data dari internet

            override suspend fun createCall(): Flow<ApiResponse<List<ValorantResponse>>> =
                remoteDataSource.getAllValorant()

            override suspend fun saveCallResult(data: List<ValorantResponse>) {
                val valorantList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertAgent(valorantList)
            }
        }.asFlow()

    override fun getFavoriteAgent(): Flow<List<Valorant>> {
        return localDataSource.getFavoriteAgent().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteAgent(valorant: Valorant, state: Boolean) {
        val valorantEntity = DataMapper.mapDomainToEntity(valorant)
        appExecutors.diskIO().execute { localDataSource.setFavoriteAgent(valorantEntity, state) }
    }
}