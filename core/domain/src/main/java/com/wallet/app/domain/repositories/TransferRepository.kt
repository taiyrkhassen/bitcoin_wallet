package com.wallet.app.domain.repositories

import com.wallet.app.domain.entities.TransactionHistory
import java.math.BigDecimal

interface TransferRepository {

    suspend fun getTransactions(): List<TransactionHistory>

    fun getTransactionFee(): BigDecimal

    suspend fun sendBitcoins(amounts: List<String>, addresses: List<String>, label: String?): String
}