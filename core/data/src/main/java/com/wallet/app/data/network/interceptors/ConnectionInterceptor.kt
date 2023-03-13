package com.wallet.app.data.network.interceptors

import com.wallet.app.domain.exceptions.NoNetworkConnectionException
import com.wallet.app.domain.repositories.ConnectionRepository
import okhttp3.Interceptor
import okhttp3.Response
import java.net.ConnectException
import java.util.concurrent.TimeUnit

internal class ConnectionInterceptor(
    private val connectionRepository: ConnectionRepository
) : Interceptor {

    private companion object {
        const val SLOW_SPEED_CONNECTION_TIMEOUT = 10
        const val SLOW_SPEED_READ_WRITE_TIMEOUT = 20

        const val FAST_SPEED_CONNECTION_TIMEOUT = 5
        const val FAST_SPEED_READ_WRITE_TIMEOUT = 10
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.toString()

        if (!connectionRepository.isConnectedToNetwork()) {
            throw NoNetworkConnectionException(url)
        }

        val connectionTimeout = when {
            connectionRepository.isNetworkSpeedFast() -> FAST_SPEED_CONNECTION_TIMEOUT
            else -> SLOW_SPEED_CONNECTION_TIMEOUT
        }

        val readWriteTimeout = when {
            connectionRepository.isNetworkSpeedFast() -> FAST_SPEED_READ_WRITE_TIMEOUT
            else -> SLOW_SPEED_READ_WRITE_TIMEOUT
        }

        val result = try {
            chain
                .withConnectTimeout(connectionTimeout, TimeUnit.SECONDS)
                .withReadTimeout(readWriteTimeout, TimeUnit.SECONDS)
                .withWriteTimeout(readWriteTimeout, TimeUnit.SECONDS)
                .proceed(request)
        } catch (_: ConnectException) {
            throw NoNetworkConnectionException(url)
        }
        return result
    }
}