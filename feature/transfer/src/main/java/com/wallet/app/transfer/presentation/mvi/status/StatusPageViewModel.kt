package com.wallet.app.transfer.presentation.mvi.status

import com.wallet.app.presentation.ui.base.BaseUiStateViewModel

abstract class StatusPageViewModel(
    initUi: StatusPageUiState
) : BaseUiStateViewModel<StatusPageUiState>(initUi) {

    abstract fun onButtonSendClicked()
}