package com.valdoang.valorantagent.favorite.di

import com.valdoang.valorantagent.favorite.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel {FavoriteViewModel(get())}
}