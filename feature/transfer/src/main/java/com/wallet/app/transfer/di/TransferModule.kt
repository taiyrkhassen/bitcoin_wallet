package com.wallet.app.transfer.di

import com.wallet.app.domain.di.BaseModule
import com.wallet.app.transfer.presentation.TransferViewModel
import com.wallet.app.transfer.presentation.TransferViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.TypeQualifier
import org.koin.dsl.module

class TransferModule : BaseModule() {
    override val module: Module = module {
        scope(TypeQualifier(TransferModule::class)) {
            viewModel<TransferViewModel> { TransferViewModelImpl(get()) }
        }
    }
}