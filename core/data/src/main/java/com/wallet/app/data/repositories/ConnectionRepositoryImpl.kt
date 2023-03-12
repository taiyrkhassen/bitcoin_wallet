package com.wallet.app.data.repositories

import android.content.Context
import com.wallet.app.data.extensions.isConnectedFast
import com.wallet.app.data.extensions.isConnectedToNetwork
import com.wallet.app.domain.repositories.ConnectionRepository

internal class ConnectionRepositoryImpl(
    private val context: Context
) : ConnectionRepository {

    override fun isConnectedToNetwork(): Boolean {
        return context.isConnectedToNetwork()
    }

    override fun isNetworkSpeedFast(): Boolean {
        return context.isConnectedFast()
    }
}