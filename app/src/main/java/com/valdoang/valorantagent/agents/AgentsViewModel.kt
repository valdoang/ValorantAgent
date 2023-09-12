package com.valdoang.valorantagent.agents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.valdoang.valorantagent.core.domain.usecase.ValorantUseCase

class AgentsViewModel(valorantUseCase: ValorantUseCase) : ViewModel() {
    val valorant = valorantUseCase.getAllAgent().asLiveData()
}