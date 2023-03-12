package com.wallet.app.domain.repositories

interface WalletRepository {
    suspend fun getBalance(): String
}