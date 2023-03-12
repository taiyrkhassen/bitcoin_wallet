package com.wallet.app.statuspage.presentation

import com.wallet.app.presentation.ui.base.BaseUiStateViewModel

abstract class StatusPageViewModel(
    initUi: StatusPageUiState
) : BaseUiStateViewModel<StatusPageUiState>(initUi) {

    abstract fun onButtonSendClicked()
}