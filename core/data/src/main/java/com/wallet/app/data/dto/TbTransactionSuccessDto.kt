package com.wallet.app.data.dto

import com.google.gson.annotations.SerializedName

data class TbTransactionSuccessDto(
    @SerializedName("data")
    val data: Data,
    @SerializedName("status")
    val status: String
) {
    data class Data(
        @SerializedName("network")
        val network: String?,
        @SerializedName("txid")
        val txid: String?,
    )

    fun getTxId() = data.txid ?: ""
}