package com.wallet.app.transfer.presentation

import com.wallet.app.presentation.ui.base.BaseUiStateViewModel

abstract class TransferViewModel(
    initUi: TransferUiState
): BaseUiStateViewModel<TransferUiState>(initUi) {

}