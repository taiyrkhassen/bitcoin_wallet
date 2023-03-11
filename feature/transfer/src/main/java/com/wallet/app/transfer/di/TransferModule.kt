package com.wallet.app.transfer.di

import com.wallet.app.domain.di.BaseModule
import com.wallet.app.transfer.interactors.TransferInteractor
import com.wallet.app.transfer.interactors.TransferInteractorImpl
import com.wallet.app.transfer.presentation.mvi.TransferViewModel
import com.wallet.app.transfer.presentation.mvi.TransferViewModelImpl
import com.wallet.app.transfer.presentation.mvi.status.StatusPageViewModel
import com.wallet.app.transfer.presentation.mvi.status.StatusPageViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.TypeQualifier
import org.koin.dsl.module

class TransferModule : BaseModule() {
    override val module: Module = module {
        factory<TransferInteractor> { TransferInteractorImpl(get(), get()) }
        scope(TypeQualifier(TransferModule::class)) {
            viewModel<TransferViewModel> { TransferViewModelImpl(get(), get()) }
        }
        scope(TypeQualifier(TransferModule::class)) {
            viewModel<StatusPageViewModel> { StatusPageViewModelImpl(get()) }
        }
    }
}