package com.wallet.app.data.repositories

import com.wallet.app.data.network.api.WalletApi
import com.wallet.app.domain.entities.History
import com.wallet.app.domain.repositories.WalletRepository
import java.math.BigDecimal

class WalletRepositoryImpl(
    private val walletApi: WalletApi
) : WalletRepository {

    override suspend fun getBalance(): BigDecimal  = BigDecimal(2)

    override suspend fun getHistory(): List<History> {
        TODO("Not yet implemented")
    }
}