package com.valdoang.valorantagent.di

import com.valdoang.valorantagent.core.domain.usecase.ValorantInteractor
import com.valdoang.valorantagent.core.domain.usecase.ValorantUseCase
import com.valdoang.valorantagent.detail.DetailValorantViewModel
import com.valdoang.valorantagent.agents.AgentsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<ValorantUseCase> { ValorantInteractor(get()) }
}

val viewModelModule = module {
    viewModel { AgentsViewModel(get()) }
    viewModel { DetailValorantViewModel(get()) }
}