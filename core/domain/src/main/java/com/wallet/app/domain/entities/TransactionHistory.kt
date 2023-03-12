package com.wallet.app.domain.entities

import java.util.Date

data class TransactionHistory(
    val id: String,
    val date: Date,
    val status: TransactionStatus,
    val amount: String
){
    enum class TransactionStatus{
        RECEIVED, SENT, UNKNOWN
    }
}