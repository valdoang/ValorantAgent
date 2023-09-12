package com.valdoang.valorantagent.core.domain.repository

import com.valdoang.valorantagent.core.data.Resource
import com.valdoang.valorantagent.core.domain.model.Valorant
import kotlinx.coroutines.flow.Flow

interface IValorantRepository {

    fun getAllAgent(): Flow<Resource<List<Valorant>>>

    fun getFavoriteAgent(): Flow<List<Valorant>>

    fun setFavoriteAgent(valorant: Valorant, state: Boolean)

}