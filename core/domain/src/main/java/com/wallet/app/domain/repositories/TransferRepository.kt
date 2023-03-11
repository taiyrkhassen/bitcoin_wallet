package com.wallet.app.domain.repositories

import java.math.BigDecimal

interface TransferRepository {
    suspend fun getTransactionFee(): BigDecimal

    suspend fun sendBitcoin()
}