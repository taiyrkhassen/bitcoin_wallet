package com.wallet.app.data.dto

import com.google.gson.annotations.SerializedName

data class TbNewAddressDto(
    @SerializedName("data")
    val data: Data,
    @SerializedName("status")
    val status: String
) {
    data class Data(
        @SerializedName("address")
        val address: String,
        @SerializedName("label")
        val label: String,
        @SerializedName("network")
        val network: String,
        @SerializedName("user_id")
        val user_id: Int
    )

    fun getAddress() = data.address
}

