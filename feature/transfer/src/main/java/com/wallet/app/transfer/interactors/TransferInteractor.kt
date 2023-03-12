package com.wallet.app.transfer.interactors

import java.math.BigDecimal

interface TransferInteractor {

    fun getTransactionFee(): BigDecimal

    suspend fun getBalance(): String

    suspend fun sendBitcoins()
}