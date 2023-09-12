package com.valdoang.valorantagent.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.valdoang.valorantagent.core.domain.usecase.ValorantUseCase

class FavoriteViewModel(valorantUseCase: ValorantUseCase) : ViewModel() {
    val favoriteValorant = valorantUseCase.getFavoriteAgent().asLiveData()
}