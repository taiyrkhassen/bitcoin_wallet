package com.wallet.app.data.network.di

import com.google.gson.Gson
import com.moczul.ok2curl.CurlInterceptor
import com.moczul.ok2curl.logger.Logger
import com.wallet.app.data.BuildConfig
import com.wallet.app.data.network.api.WalletApi
import com.wallet.app.domain.di.BaseModule
import okhttp3.ConnectionPool
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

class NetworkModule : BaseModule() {

    override val module: Module = module {
        factory { provideWalletApi(get()) }
        factory { provideConverterFactory(get()) }
        factory { provideRetrofit(get()) }
    }

    private fun provideConverterFactory(gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }

    private fun provideRetrofit(
        gsonConverterFactory: Converter.Factory,
        vararg interceptors: Interceptor,
    ): Retrofit {
        val client = buildOkHttpClient(*interceptors)
        return buildRetrofit(client, gsonConverterFactory)
    }

    private fun buildOkHttpClient(vararg interceptors: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .retryOnConnectionFailure()
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

    private fun OkHttpClient.Builder.retryOnConnectionFailure(): OkHttpClient.Builder {
        this.retryOnConnectionFailure(true)
        this.connectionPool(ConnectionPool(0, 1, TimeUnit.NANOSECONDS))
        return this
    }

    private fun buildRetrofit(
        client: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            /**
             * baseUrl is not used here because domain provided dynamically in interceptor
             * @see DomainInterceptor
             */
            .baseUrl("http://localhost/")
            .client(client)
            .addConverterFactory(converterFactory)
            .build()
    }

    private fun provideWalletApi(retrofit: Retrofit): WalletApi {
        return retrofit.create(WalletApi::class.java)
    }
}