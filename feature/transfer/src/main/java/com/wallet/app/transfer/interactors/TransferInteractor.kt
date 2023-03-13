package com.wallet.app.transfer.interactors

import java.math.BigDecimal

interface TransferInteractor {

    fun getTransactionFee(): BigDecimal

    suspend fun getBalance(): BigDecimal

    suspend fun sendBitcoins(amount: List<String>, addresses: List<String>, label: String? = null): String
}