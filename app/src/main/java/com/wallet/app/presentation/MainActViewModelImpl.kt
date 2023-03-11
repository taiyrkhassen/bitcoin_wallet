package com.wallet.app.presentation

import com.wallet.app.presentation.navigation.Navigator
import com.wallet.app.presentation.navigation.SplashScreen

internal class MainActViewModelImpl(
    private val navigator: Navigator
) : MainActViewModel(MainActUiState()) {

    init {
        navigator.newRootChain(SplashScreen)
    }
}