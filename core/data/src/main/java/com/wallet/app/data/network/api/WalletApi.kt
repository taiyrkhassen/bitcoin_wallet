package com.wallet.app.data.network.api

import com.wallet.app.config.TESTNET_BITCOIN_KEY
import com.wallet.app.data.dto.TbBalanceDto
import com.wallet.app.data.dto.TbNewAddressDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WalletApi {
    @GET("api/v2/get_balance/")
    fun getTbBalance(
        @Query("api_key") apiKey: String = TESTNET_BITCOIN_KEY
    ): TbBalanceDto

    @GET("api/v2/get_new_address/")
    fun getTbAddress(
        @Query("api_key") apiKey: String = TESTNET_BITCOIN_KEY
    ): TbNewAddressDto

}