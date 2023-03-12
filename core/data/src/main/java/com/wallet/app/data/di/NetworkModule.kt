package com.wallet.app.data.di

import com.google.gson.Gson
import com.moczul.ok2curl.CurlInterceptor
import com.moczul.ok2curl.logger.Logger
import com.wallet.app.data.BuildConfig
import com.wallet.app.data.network.api.TransactionsApi
import com.wallet.app.data.network.api.WalletApi
import com.wallet.app.data.network.interceptors.ConnectionInterceptor
import com.wallet.app.domain.di.BaseModule
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

class NetworkModule : BaseModule() {

    private companion object {
        const val BLOCK_IO_RETROFIT = "block_io_retrofit"
        const val BLOCK_IO_BASE_URL = "https://block.io/"
    }

    override val module: Module = module {
        single { ConnectionInterceptor(get()) }
        single { HttpLoggingInterceptor() }
        factory { provideConverterFactory(get()) }

        factory(named(BLOCK_IO_RETROFIT)) {
            provideRetrofit(
                gsonConverterFactory = get(),
                interceptors = listOf(get<HttpLoggingInterceptor>()).toTypedArray(),
                BLOCK_IO_BASE_URL
            )
        }

        //api
        factory { provideWalletApi(get(named(BLOCK_IO_RETROFIT))) }
        factory { provideTransactionApi(get(named(BLOCK_IO_RETROFIT))) }
    }

    private fun provideConverterFactory(gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }

    private fun provideRetrofit(
        gsonConverterFactory: Converter.Factory,
        vararg interceptors: Interceptor,
        baseUrl: String
    ): Retrofit {
        val client = buildOkHttpClient(*interceptors)
        return buildRetrofit(client, gsonConverterFactory, baseUrl)
    }

    private fun buildOkHttpClient(vararg interceptors: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .apply { interceptors.forEach { addInterceptor(it) } }
            .apply {
                if (BuildConfig.DEBUG) {
                    addNetworkInterceptor(CurlInterceptor(object : Logger {
                        override fun log(message: String) {
                            Timber.i(message)
                        }
                    }))
                    addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                }
            }
            .build()
    }

    private fun buildRetrofit(
        client: OkHttpClient,
        converterFactory: Converter.Factory,
        baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(converterFactory)
            .build()
    }

    private fun provideWalletApi(retrofit: Retrofit): WalletApi {
        return retrofit.create(WalletApi::class.java)
    }

    private fun provideTransactionApi(retrofit: Retrofit): TransactionsApi {
        return retrofit.create(TransactionsApi::class.java)
    }
}