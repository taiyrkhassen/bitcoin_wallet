package com.wallet.app.domain.repositories

interface ConnectionRepository {

    fun isConnectedToNetwork(): Boolean

    fun isNetworkSpeedFast(): Boolean
}