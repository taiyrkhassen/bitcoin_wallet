package com.wallet.app.data.network.api

import com.wallet.app.config.TESTNET_BITCOIN_KEY
import com.wallet.app.data.dto.TbTransactionDto
import com.wallet.app.data.requests.SignedTransactionRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TransactionsApi {

    @GET("api/v2/get_transactions/")
    fun getTransactions(
        @Query("api_key") apiKey: String = TESTNET_BITCOIN_KEY,
        @Query("type") type: String
    ): TbTransactionDto


    @POST("api/v2/submit_transaction/")
    fun submitTransaction(
        @Query("api_key") apiKey: String = TESTNET_BITCOIN_KEY,
        @Body signedTransaction: SignedTransactionRequest
    )
}