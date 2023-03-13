package com.wallet.app.data.repositories

import com.wallet.app.config.TESTNET_BITCOIN_KEY
import com.wallet.app.config.TRANSACTION_FEE
import com.wallet.app.config.TRANSACTION_RECEIVED
import com.wallet.app.config.TRANSACTION_SENT
import com.wallet.app.data.network.api.TransactionsApi
import com.wallet.app.domain.entities.TransactionHistory
import com.wallet.app.domain.repositories.TransferRepository
import lib.blockIo.BlockIo
import org.json.simple.JSONObject
import java.math.BigDecimal

class TransferRepositoryImpl(
    private val transferApi: TransactionsApi,
    private val blockIo: BlockIo
) : TransferRepository {

    override suspend fun getTransactions(): List<TransactionHistory> {
        val sent = transferApi.getTransactions(type = TRANSACTION_SENT)
        val received = transferApi.getTransactions(type = TRANSACTION_RECEIVED)
        val result = sent.data.copy(txs = sent.data.txs.plus(received.data.txs)
            .sortedByDescending { it.time })
        return result.txs.map { it.toDomain() }
    }

    override fun getTransactionFee() = BigDecimal(TRANSACTION_FEE)

    override suspend fun sendBitcoins(
        amounts: List<String>,
        addresses: List<String>,
        label: String?
    ): String {
        val preparedData = transferApi.prepareTransaction(TESTNET_BITCOIN_KEY, amounts, addresses)

        val jsonData = label?.let {
            blockIo.PrepareTransaction(
                JSONObject(
                    mapOf(
                        "label" to it,
                        "to_addresses" to addresses,
                        "amounts" to amounts
                    )
                )
            )
        } ?: blockIo.PrepareTransaction(
            JSONObject(
                mapOf(
                    "to_addresses" to addresses.first(),
                    "amounts" to amounts.first()
                )
            )
        )

        val createdSignedTr = blockIo.CreateAndSignTransaction(jsonData)
        val json = JSONObject(mapOf("transaction_data" to createdSignedTr))
        return transferApi.submitTransaction(signedTransaction = json).getTxId()
    }
}