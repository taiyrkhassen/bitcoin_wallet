package com.wallet.app.home.interactors

import com.wallet.app.domain.entities.Transaction
import java.math.BigDecimal

interface HomeInteractor {
    suspend fun getBalance(): BigDecimal

    suspend fun getTransactions(): List<Transaction>
}