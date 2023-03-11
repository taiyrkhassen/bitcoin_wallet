package com.wallet.app.home.interactors

import com.wallet.app.domain.repositories.WalletRepository

class HomeInteractorImpl(
    private val walletRepository: WalletRepository
) : HomeInteractor {

    override suspend fun getBalance() = walletRepository.getBalance()

    override suspend fun getTransactions() = walletRepository.getTransactions()
}