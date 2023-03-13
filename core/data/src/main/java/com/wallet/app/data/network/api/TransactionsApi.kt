package com.wallet.app.data.network.api

import com.wallet.app.config.TESTNET_BITCOIN_KEY
import com.wallet.app.data.dto.TbPreparationDto
import com.wallet.app.data.dto.TbTransactionDto
import com.wallet.app.data.dto.TbTransactionSuccessDto
import org.json.simple.JSONObject
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TransactionsApi {

    @GET("api/v2/get_transactions/")
    suspend fun getTransactions(
        @Query("api_key") apiKey: String = TESTNET_BITCOIN_KEY,
        @Query("type") type: String
    ): TbTransactionDto

    @POST("/api/v2/prepare_transaction/")
    suspend fun prepareTransaction(
        @Query("api_key") apiKey: String = TESTNET_BITCOIN_KEY,
        @Query("amounts") amounts: List<String>,
        @Query("to_addresses") addresses: List<String>
    ): TbPreparationDto

    @POST("api/v2/submit_transaction/")
    suspend fun submitTransaction(
        @Query("api_key") apiKey: String = TESTNET_BITCOIN_KEY,
        @Body signedTransaction: JSONObject
    ): TbTransactionSuccessDto
}