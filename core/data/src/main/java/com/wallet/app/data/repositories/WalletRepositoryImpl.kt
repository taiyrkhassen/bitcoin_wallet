package com.wallet.app.data.repositories

import com.wallet.app.data.network.api.WalletApi
import com.wallet.app.domain.entities.Transaction
import com.wallet.app.domain.repositories.WalletRepository
import kotlinx.coroutines.delay
import java.math.BigDecimal
import java.util.Date

class WalletRepositoryImpl(
    private val walletApi: WalletApi
) : WalletRepository {

    override suspend fun getBalance(): BigDecimal {
        return BigDecimal(210020)
    }

    override suspend fun getTransactions(): List<Transaction> {
        delay(3000)
        return listOf(
            Transaction(
                id = "123",
                date = Date(),
                status = Transaction.TransactionStatus.RECEIVED,
                amount = BigDecimal(9.2329854)
            ),
            Transaction(
                id = "124",
                date = Date(),
                status = Transaction.TransactionStatus.SENT,
                amount = BigDecimal(2.2329854)
            ),
            Transaction(
                id = "125",
                date = Date(),
                status = Transaction.TransactionStatus.SENT,
                amount = BigDecimal(8.2329854)
            )
        )
    }
}