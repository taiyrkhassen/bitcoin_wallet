package com.wallet.app.transfer.interactors

import com.wallet.app.domain.repositories.TransferRepository
import com.wallet.app.domain.repositories.WalletRepository
import java.math.BigDecimal

class TransferInteractorImpl(
    private val walletRepository: WalletRepository,
    private val transferRepository: TransferRepository
) : TransferInteractor {

    override suspend fun getTransactionFee() = transferRepository.getTransactionFee()

    override suspend fun getBalance() = walletRepository.getBalance()

    override suspend fun getMinMax(): BigDecimal {
        TODO("Not yet implemented")
    }

    override suspend fun sendBitcoins() {
        transferRepository.sendBitcoin()
    }
}