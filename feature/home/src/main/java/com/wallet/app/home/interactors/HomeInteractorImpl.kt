package com.wallet.app.home.interactors

import com.wallet.app.domain.repositories.TransferRepository
import com.wallet.app.domain.repositories.WalletRepository

class HomeInteractorImpl(
    private val walletRepository: WalletRepository,
    private val transferRepository: TransferRepository
) : HomeInteractor {

    override suspend fun getBalance() = walletRepository.getBalance()

    override suspend fun getTransactions() = transferRepository.getTransactions()
}