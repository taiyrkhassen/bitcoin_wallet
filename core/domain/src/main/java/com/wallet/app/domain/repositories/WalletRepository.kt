package com.wallet.app.domain.repositories

import java.math.BigDecimal

interface WalletRepository {
    suspend fun getBalance(): BigDecimal

    suspend fun getAddress(): String
}