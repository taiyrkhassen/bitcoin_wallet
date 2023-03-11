package com.wallet.app.transfer.presentation.mvi.status

import com.wallet.app.presentation.navigation.Navigator

class StatusPageViewModelImpl(
    private val navigator: Navigator
): StatusPageViewModel(StatusPageUiState()) {

    override fun onButtonSendClicked() {
        navigator.exit()
    }
}