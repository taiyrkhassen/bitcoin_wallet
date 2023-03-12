package com.wallet.app.data.repositories

import com.wallet.app.config.TRANSACTION_RECEIVED
import com.wallet.app.config.TRANSACTION_SENT
import com.wallet.app.data.network.api.TransactionsApi
import com.wallet.app.domain.entities.TransactionHistory
import com.wallet.app.domain.repositories.TransferRepository
import java.math.BigDecimal

class TransferRepositoryImpl(
    private val transferApi: TransactionsApi
) : TransferRepository {

    override suspend fun getTransactions(): List<TransactionHistory> {
        val sent = transferApi.getTransactions(type = TRANSACTION_SENT)
        val received = transferApi.getTransactions(type = TRANSACTION_RECEIVED)
        val result = sent.data.copy(txs = sent.data.txs.plus(received.data.txs)
            .sortedBy { it.time })
        return result.txs.map { it.toDomain() }
    }

    override fun getTransactionFee() = BigDecimal(0.000001)

    override suspend fun sendBitcoins() {

    }
}