package com.wallet.app.data.repositories

import com.wallet.app.data.network.api.WalletApi
import com.wallet.app.domain.repositories.WalletRepository
import java.math.BigDecimal

class WalletRepositoryImpl(
    private val walletApi: WalletApi
) : WalletRepository {

    override suspend fun getBalance(): BigDecimal {
        return walletApi.getTbBalance().getBalance().toBigDecimal()
    }

    override suspend fun getAddress(): String {
        return walletApi.getTbAddress().getAddress()
    }
}