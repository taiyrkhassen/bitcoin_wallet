package com.wallet.app.domain.entities

import java.math.BigDecimal
import java.util.Date

data class History(
    val date: Date,
    val name: String,
    val description: String,
    val sum: BigDecimal,
    val currency: String
)