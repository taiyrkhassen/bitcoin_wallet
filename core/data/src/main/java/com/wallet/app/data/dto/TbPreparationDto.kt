package com.wallet.app.data.dto

import com.google.gson.annotations.SerializedName

data class TbPreparationDto(
    @SerializedName("data")
    val data: Data,
    @SerializedName("status")
    val status: String
)

data class Data(
    @SerializedName("estimated_tx_size")
    val estimated_tx_size: Int,
    @SerializedName("expected_unsigned_txid")
    val expected_unsigned_txid: String,
    @SerializedName("input_address_data")
    val input_address_data: List<InputAddressData>,
    @SerializedName("inputs")
    val inputs: List<Input>,
    @SerializedName("network")
    val network: String,
    @SerializedName("outputs")
    val outputs: List<Output>,
    @SerializedName("tx_type")
    val tx_type: String,
    @SerializedName("user_key")
    val user_key: UserKey
)

data class InputAddressData(
    @SerializedName("address")
    val address: String,
    @SerializedName("address_type")
    val address_type: String,
    @SerializedName("public_keys")
    val public_keys: List<String>,
    @SerializedName("required_signatures")
    val required_signatures: Int
)

data class Input(
    @SerializedName("input_index")
    val input_index: Int,
    @SerializedName("input_value")
    val input_value: String,
    @SerializedName("previous_output_index")
    val previous_output_index: Int,
    @SerializedName("previous_txid")
    val previous_txid: String,
    @SerializedName("spending_address")
    val spending_address: String
)

data class Output(
    @SerializedName("output_category")
    val output_category: String,
    @SerializedName("output_index")
    val output_index: Int,
    @SerializedName("output_value")
    val output_value: String,
    @SerializedName("receiving_address")
    val receiving_address: String
)

data class UserKey(
    @SerializedName("algorithm")
    val algorithm: Algorithm,
    @SerializedName("encrypted_passphrase")
    val encrypted_passphrase: String,
    @SerializedName("public_key")
    val public_key: String
)

data class Algorithm(
    @SerializedName("aes_auth_data")
    val aes_auth_data: String,
    @SerializedName("aes_auth_tag")
    val aes_auth_tag: String,
    @SerializedName("aes_cipher")
    val aes_cipher: String,
    @SerializedName("aes_iv")
    val aes_iv: String,
    @SerializedName("pbkdf2_hash_function")
    val pbkdf2_hash_function: String,
    @SerializedName("pbkdf2_iterations")
    val pbkdf2_iterations: Int,
    @SerializedName("pbkdf2_phase1_key_length")
    val pbkdf2_phase1_key_length: Int,
    @SerializedName("pbkdf2_phase2_key_length")
    val pbkdf2_phase2_key_length: Int,
    @SerializedName("pbkdf2_salt")
    val pbkdf2_salt: String
)