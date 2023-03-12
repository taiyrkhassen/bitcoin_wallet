package com.wallet.app.presentation.extension

import java.math.BigDecimal
import java.math.RoundingMode

fun BigDecimal.toAmount(currency: String = "tBTC") = "${this.setScale(5, RoundingMode.DOWN)} $currency"

fun String.toAmount(currency: String = "tBTC") = "$this $currency"