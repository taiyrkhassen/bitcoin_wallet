package com.wallet.app.presentation.extension

import java.math.BigDecimal
import java.math.RoundingMode

fun BigDecimal.toAmount(currency: String = "BTC") = "${this.setScale(5, RoundingMode.DOWN).toString()} $currency"