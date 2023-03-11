package com.wallet.app.home.di

import com.wallet.app.domain.di.BaseModule
import com.wallet.app.home.interactors.HomeInteractor
import com.wallet.app.home.interactors.HomeInteractorImpl
import com.wallet.app.home.presentation.mvi.HomeViewModel
import com.wallet.app.home.presentation.mvi.HomeViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.TypeQualifier
import org.koin.dsl.module

class HomeModule : BaseModule() {
    override val module: Module = module {
        factory<HomeInteractor> { HomeInteractorImpl(get()) }
        scope(TypeQualifier(HomeModule::class)) {
            viewModel<HomeViewModel> { HomeViewModelImpl(get()) }
        }
    }
}