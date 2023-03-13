package com.wallet.app.statuspage.di

import com.wallet.app.domain.di.BaseModule
import com.wallet.app.statuspage.presentation.StatusPageViewModel
import com.wallet.app.statuspage.presentation.StatusPageViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.TypeQualifier
import org.koin.dsl.module

class StatusModule : BaseModule() {
    override val module: Module = module {
        scope(TypeQualifier(StatusModule::class)) {
            viewModel<StatusPageViewModel> { StatusPageViewModelImpl(get()) }
        }
    }
}