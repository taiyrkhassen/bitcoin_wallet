package com.wallet.app.domain.entities

import java.math.BigDecimal
import java.util.Date

data class Transaction(
    val id: String,
    val date: Date,
    val status: TransactionStatus,
    val amount: BigDecimal
){
    enum class TransactionStatus{
        RECEIVED, SENT
    }
}