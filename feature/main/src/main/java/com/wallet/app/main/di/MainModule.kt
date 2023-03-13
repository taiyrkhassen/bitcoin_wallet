package com.wallet.app.main.di

import com.wallet.app.domain.di.BaseModule
import com.wallet.app.main.presentation.MainViewModel
import com.wallet.app.main.presentation.MainViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.TypeQualifier
import org.koin.dsl.module

class MainModule : BaseModule() {
    override val module: Module = module {
        scope(TypeQualifier(MainModule::class)) {
            viewModel<MainViewModel> { MainViewModelImpl(get()) }
        }
    }
}