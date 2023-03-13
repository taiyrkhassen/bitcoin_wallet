package com.wallet.app.home.interactors

import com.wallet.app.domain.entities.TransactionHistory
import java.math.BigDecimal

interface HomeInteractor {
    suspend fun getBalance(): BigDecimal

    suspend fun getTransactions(): List<TransactionHistory>
}