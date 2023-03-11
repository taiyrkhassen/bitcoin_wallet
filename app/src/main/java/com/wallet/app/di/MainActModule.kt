package com.wallet.app.di

import com.wallet.app.domain.di.BaseModule
import com.wallet.app.presentation.MainActViewModel
import com.wallet.app.presentation.MainActViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.TypeQualifier
import org.koin.dsl.module

class MainActModule : BaseModule() {
    override val module: Module = module {
        scope(TypeQualifier(MainActModule::class)) {
            viewModel<MainActViewModel> { MainActViewModelImpl(get()) }
        }
    }
}