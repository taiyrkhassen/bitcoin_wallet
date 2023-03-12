package com.wallet.app.data.repositories

import com.wallet.app.data.network.api.WalletApi
import com.wallet.app.domain.entities.TransactionHistory
import com.wallet.app.domain.repositories.WalletRepository
import kotlinx.coroutines.delay
import java.math.BigDecimal
import java.util.Date

class WalletRepositoryImpl(
    private val walletApi: WalletApi
) : WalletRepository {

    override suspend fun getBalance(): String {
        return walletApi.getTbBalance().getBalance()
    }
}