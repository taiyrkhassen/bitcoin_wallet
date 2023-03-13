package com.wallet.app.data.dto

import com.google.gson.annotations.SerializedName

data class TbTransactionDto(
    @SerializedName("data")
    val data: TbTransactionDataDto,
    @SerializedName("status")
    val status: String
)