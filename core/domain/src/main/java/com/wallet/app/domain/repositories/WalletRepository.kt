package com.wallet.app.domain.repositories

import com.wallet.app.domain.entities.Transaction
import java.math.BigDecimal

interface WalletRepository {
    suspend fun getBalance(): BigDecimal

    suspend fun getTransactions(): List<Transaction>
}