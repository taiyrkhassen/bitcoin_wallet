package com.wallet.app.data.dto

import com.google.gson.annotations.SerializedName

data class TbBalanceDto(
    @SerializedName("data")
    val data: Data,
    @SerializedName("data")
    val status: String
) {
    data class Data(
        @SerializedName("available_balance")
        val availableBalance: String,
        @SerializedName("network")
        val network: String,
        @SerializedName("pending_received_balance")
        val pendingBalance: String
    )

    fun getBalance() = data.availableBalance
}