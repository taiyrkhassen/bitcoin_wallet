package com.wallet.app.splash.interactors

import com.wallet.app.domain.repositories.WalletRepository

internal class SplashInteractorImpl(
    private val walletRepository: WalletRepository
) : SplashInteractor {

    override suspend fun getBalance() = walletRepository.getBalance()
    override suspend fun getAddress() = walletRepository.getAddress()

}