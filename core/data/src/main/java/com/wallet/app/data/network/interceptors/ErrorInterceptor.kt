package com.wallet.app.data.network.interceptors

import com.wallet.app.data.dto.TransactionErrorDto
import com.wallet.app.data.extensions.fromJson
import com.wallet.app.domain.exceptions.WalletHttpException
import okhttp3.Interceptor
import okhttp3.Response

class ErrorInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        return if (response.isSuccessful) {
            response
        } else {
            throw response.toMwlHttpException()
        }
    }

    private fun Response.toMwlHttpException(): WalletHttpException {
        val error = body?.fromJson(TransactionErrorDto::class.java)
        return if (error != null) {
            WalletHttpException(error.data.errorMessage)
        } else {
            WalletHttpException("unknown error")
        }
    }
}