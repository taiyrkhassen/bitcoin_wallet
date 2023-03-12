package com.wallet.app.presentation.extension

import java.math.BigDecimal
import java.math.RoundingMode

fun BigDecimal.toAmount(currency: String = "tBTC") = "${this.setScale(6, RoundingMode.UP)} $currency"

fun String.toAmount(currency: String = "tBTC") = "$this $currency"

fun BigDecimal.isZero() = compareTo(BigDecimal.ZERO) == 0

fun BigDecimal.isLessThan(value: BigDecimal?) = compareTo(value) < 0