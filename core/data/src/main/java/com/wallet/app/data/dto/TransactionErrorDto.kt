package com.wallet.app.data.dto

import com.google.gson.annotations.SerializedName

data class TransactionErrorDto(
    @SerializedName("data")
    val data: Data,
    @SerializedName("status")
    val status: String
) {
    data class Data(
        @SerializedName("error_message")
        val errorMessage: String,
    )
}
