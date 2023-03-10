package com.wallet.app.splash.di

import com.wallet.app.domain.di.BaseModule
import com.wallet.app.splash.interactors.SplashInteractor
import com.wallet.app.splash.interactors.SplashInteractorImpl
import com.wallet.app.splash.presentation.SplashViewModel
import com.wallet.app.splash.presentation.SplashViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.TypeQualifier
import org.koin.dsl.module

class SplashModule : BaseModule() {
    override val module: Module = module {
        factory<SplashInteractor> { SplashInteractorImpl(get()) }
        scope(TypeQualifier(SplashModule::class)) {
            viewModel<SplashViewModel> { SplashViewModelImpl(get(), get()) }
        }
    }
}