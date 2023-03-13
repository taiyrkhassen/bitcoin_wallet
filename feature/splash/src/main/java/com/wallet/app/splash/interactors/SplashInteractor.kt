package com.wallet.app.splash.interactors

import java.math.BigDecimal

internal interface SplashInteractor {
    suspend fun getBalance(): BigDecimal

    suspend fun getAddress(): String
}