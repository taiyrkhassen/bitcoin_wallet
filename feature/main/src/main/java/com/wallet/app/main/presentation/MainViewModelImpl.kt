package com.wallet.app.main.presentation

import com.wallet.app.presentation.navigation.Navigator

class MainViewModelImpl(
    private val navigator: Navigator
) : MainViewModel(MainUiState()) {

}