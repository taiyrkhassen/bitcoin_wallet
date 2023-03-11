package com.wallet.app.data.di

import com.wallet.app.data.repositories.TransferRepositoryImpl
import com.wallet.app.data.repositories.WalletRepositoryImpl
import com.wallet.app.domain.di.BaseModule
import com.wallet.app.domain.repositories.TransferRepository
import com.wallet.app.domain.repositories.WalletRepository
import org.koin.core.module.Module
import org.koin.dsl.module

class RepositoryModule : BaseModule() {
    override val module: Module = module {
        single<WalletRepository> {
            WalletRepositoryImpl(get())
        }
        single<TransferRepository> {
            TransferRepositoryImpl(get())
        }
    }
}