package com.valdoang.valorantagent.detail

import androidx.lifecycle.ViewModel
import com.valdoang.valorantagent.core.domain.model.Valorant
import com.valdoang.valorantagent.core.domain.usecase.ValorantUseCase

class DetailValorantViewModel(private val valorantUseCase: ValorantUseCase) : ViewModel() {
    fun setFavoriteTourism(valorant: Valorant, newStatus:Boolean) =
        valorantUseCase.setFavoriteAgent(valorant, newStatus)
}