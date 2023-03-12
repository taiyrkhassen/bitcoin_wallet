package com.wallet.app.transfer.presentation.mvi

import com.wallet.app.presentation.ui.base.BaseUiState
import java.math.BigDecimal

data class TransferUiState(
    val balance: String? = null,
    val isButtonEnabled: Boolean = false,
    val showExceptionMessage: String? = null,
    val shimmerIsVisible: Boolean = true,
    val transactionFee: BigDecimal = BigDecimal(0),
    val amountForFees: BigDecimal = BigDecimal(0),
) : BaseUiState()