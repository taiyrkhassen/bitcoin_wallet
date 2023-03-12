package com.wallet.app.data.dto

import com.google.gson.annotations.SerializedName

data class TbTransactionDataDto(
    @SerializedName("network")
    val network: String,
    @SerializedName("txs")
    val txs: List<Tx>
)