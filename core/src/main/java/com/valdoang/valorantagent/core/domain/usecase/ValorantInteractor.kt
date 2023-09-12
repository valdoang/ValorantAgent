package com.valdoang.valorantagent.core.domain.usecase

import com.valdoang.valorantagent.core.domain.model.Valorant
import com.valdoang.valorantagent.core.domain.repository.IValorantRepository

class ValorantInteractor(private val valorantRepository: IValorantRepository): ValorantUseCase {

    override fun getAllAgent() = valorantRepository.getAllAgent()

    override fun getFavoriteAgent() = valorantRepository.getFavoriteAgent()

    override fun setFavoriteAgent(valorant: Valorant, state: Boolean) = valorantRepository.setFavoriteAgent(valorant, state)
}