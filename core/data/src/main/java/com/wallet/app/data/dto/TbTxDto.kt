package com.wallet.app.data.dto

import com.google.gson.annotations.SerializedName
import com.wallet.app.domain.entities.TransactionHistory
import java.util.Date

data class Tx(
    @SerializedName("amounts_sent")
    val amounts_sent: List<AmountDto>? = null,
    @SerializedName("amounts_received")
    val amounts_received: List<AmountDto>? = null,
    @SerializedName("confidence")
    val confidence: Double? = null,
    @SerializedName("confirmations")
    val confirmations: Int? = null,
    @SerializedName("from_green_address")
    val from_green_address: Boolean? = null,
    @SerializedName("propagated_by_nodes")
    val propagated_by_nodes: Int? = null,
    @SerializedName("senders")
    val senders: List<String>? = null,
    @SerializedName("time")
    val time: Long?,
    @SerializedName("total_amount_sent")
    val total_amount_sent: String? = null,
    @SerializedName("txid")
    val txid: String? = null
) {
    fun toDomain() = TransactionHistory(
        id = txid ?: "null",
        date = time?.let {  Date(Date().time - it) } ?: Date(),
        status = getType(),
        amount = getAmount()
    )

    private fun getType(): TransactionHistory.TransactionStatus {
        return when {
            amounts_sent == null && amounts_received != null -> TransactionHistory.TransactionStatus.RECEIVED
            amounts_sent != null && amounts_received == null -> TransactionHistory.TransactionStatus.SENT
            else -> TransactionHistory.TransactionStatus.UNKNOWN
        }
    }

    private fun getAmount(): String {
        return when (getType()) {
            TransactionHistory.TransactionStatus.RECEIVED -> {
                amounts_received?.first()?.amount ?: "null"
            }

            TransactionHistory.TransactionStatus.SENT -> total_amount_sent ?: "null"
            else -> "null"
        }
    }
}


data class AmountDto(
    @SerializedName("amount")
    val amount: String,
    @SerializedName("recipient")
    val recipient: String
)