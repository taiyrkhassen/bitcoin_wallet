package com.wallet.app.domain.repositories

import com.wallet.app.domain.entities.History
import java.math.BigDecimal

interface WalletRepository {
    suspend fun getBalance(): BigDecimal

    suspend fun getHistory(): List<History>
}