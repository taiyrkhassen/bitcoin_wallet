package com.wallet.app.statuspage.presentation

import com.wallet.app.presentation.navigation.Navigator

class StatusPageViewModelImpl(
    private val navigator: Navigator
): StatusPageViewModel(StatusPageUiState()) {

    override fun onButtonSendClicked() {
        navigator.exit()
    }
}