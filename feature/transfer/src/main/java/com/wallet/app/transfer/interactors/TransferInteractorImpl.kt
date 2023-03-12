package com.wallet.app.transfer.interactors

import com.wallet.app.domain.repositories.TransferRepository
import com.wallet.app.domain.repositories.WalletRepository

class TransferInteractorImpl(
    private val walletRepository: WalletRepository,
    private val transferRepository: TransferRepository
) : TransferInteractor {

    override fun getTransactionFee() = transferRepository.getTransactionFee()

    override suspend fun getBalance() = walletRepository.getBalance()

    override suspend fun sendBitcoins(
        amount: List<String>,
        addresses: List<String>,
        label: String?
    ) {
        transferRepository.sendBitcoins(amount, addresses, label)
    }
}