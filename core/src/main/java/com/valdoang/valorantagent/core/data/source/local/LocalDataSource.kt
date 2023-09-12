package com.valdoang.valorantagent.core.data.source.local

import com.valdoang.valorantagent.core.data.source.local.entity.ValorantEntity
import com.valdoang.valorantagent.core.data.source.local.room.ValorantDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val valorantDao: ValorantDao) {

    fun getAllAgent(): Flow<List<ValorantEntity>> = valorantDao.getAllAgent()

    fun getFavoriteAgent(): Flow<List<ValorantEntity>> = valorantDao.getFavoriteAgent()

    suspend fun insertAgent(valorantList: List<ValorantEntity>) = valorantDao.insertAgent(valorantList)

    fun setFavoriteAgent(valorant: ValorantEntity, newState: Boolean) {
        valorant.isFavorite = newState
        valorantDao.updateFavoriteAgent(valorant)
    }
}