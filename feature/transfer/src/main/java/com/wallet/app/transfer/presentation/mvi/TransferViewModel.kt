package com.wallet.app.transfer.presentation.mvi

import com.wallet.app.presentation.ui.base.BaseUiStateViewModel

abstract class TransferViewModel(
    initUi: TransferUiState
) : BaseUiStateViewModel<TransferUiState>(initUi) {
    abstract fun sendBitcoins()

    abstract fun onAmountChanged(email: String?)

    abstract fun onAddressChanged(address: String?)

    abstract fun onNoteChanged(note: String?)
}