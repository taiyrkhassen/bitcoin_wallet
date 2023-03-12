package com.wallet.app.data.requests

import com.google.gson.annotations.SerializedName

data class SignedTransactionRequest(
    @SerializedName("signatures")
    val signatures: List<Signature>,
    @SerializedName("tx_hex")
    val tx_hex: String,
    @SerializedName("tx_type")
    val tx_type: String
)

data class Signature(
    @SerializedName("input_index")
    val input_index: Int,
    @SerializedName("public_key")
    val public_key: String,
    @SerializedName("signature")
    val signature: String
)