package com.wallet.app.transfer.interactors

import java.math.BigDecimal

interface TransferInteractor {

    suspend fun getTransactionFee(): BigDecimal

    suspend fun getBalance(): BigDecimal

    suspend fun getMinMax(): BigDecimal

    suspend fun sendBitcoins()
}