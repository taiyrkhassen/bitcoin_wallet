package com.wallet.app.transfer.presentation

import com.wallet.app.presentation.navigation.Navigator

class TransferViewModelImpl(
    private val navigator: Navigator
) : TransferViewModel(TransferUiState()) {

}