package com.wallet.app.data.repositories

import com.wallet.app.data.network.api.WalletApi
import com.wallet.app.domain.repositories.TransferRepository
import java.math.BigDecimal

class TransferRepositoryImpl(
    private val walletApi: WalletApi
) : TransferRepository {

    override suspend fun getTransactionFee() = BigDecimal(2)

    override suspend fun sendBitcoins() {
        BigDecimal(2)
    }
}