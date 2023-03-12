package com.wallet.app.transfer.presentation.mvi

import com.wallet.app.presentation.ui.base.BaseUiState
import java.math.BigDecimal

data class TransferUiState(
    val balance: BigDecimal = BigDecimal(0),
    val isButtonEnabled: Boolean = false,
    val showExceptionMessage: String? = null,
    val showError: Boolean = false,
    val shimmerIsVisible: Boolean = true,
    val transactionFee: BigDecimal = BigDecimal(0)
) : BaseUiState()