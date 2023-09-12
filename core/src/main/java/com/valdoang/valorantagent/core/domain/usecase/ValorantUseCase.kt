package com.valdoang.valorantagent.core.domain.usecase

import com.valdoang.valorantagent.core.data.Resource
import com.valdoang.valorantagent.core.domain.model.Valorant
import kotlinx.coroutines.flow.Flow

interface ValorantUseCase {
    fun getAllAgent(): Flow<Resource<List<Valorant>>>
    fun getFavoriteAgent(): Flow<List<Valorant>>
    fun setFavoriteAgent(valorant: Valorant, state: Boolean)
}