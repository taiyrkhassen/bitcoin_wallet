package com.wallet.app.home.presentation.mvi

import com.wallet.app.home.presentation.adapters.wrapper.TransactionWrapper
import com.wallet.app.presentation.ui.base.BaseUiState
import java.math.BigDecimal

data class HomeUiState(
    val transactions: List<TransactionWrapper> = emptyList(),
    val showExceptionMessage: String? = null,
    val shimmerIsVisible: Boolean = true,
    val emptyStateVisible: Boolean = false,
    val balance: BigDecimal = BigDecimal(0),
    val btcNumber: BigDecimal = BigDecimal(0),
) : BaseUiState()