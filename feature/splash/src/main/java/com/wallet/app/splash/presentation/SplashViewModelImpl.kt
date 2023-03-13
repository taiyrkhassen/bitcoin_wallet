package com.wallet.app.splash.presentation

import androidx.lifecycle.viewModelScope
import com.wallet.app.presentation.extension.subscribe
import com.wallet.app.presentation.navigation.MainScreen
import com.wallet.app.presentation.navigation.Navigator
import com.wallet.app.splash.interactors.SplashInteractor

internal class SplashViewModelImpl(
    private val interactor: SplashInteractor,
    private val navigator: Navigator
) : SplashViewModel(SplashUiState()) {

    init {
        loadBalance()
    }

    private fun loadBalance() {
        viewModelScope.subscribe(
            doAction = {
                interactor.getAddress()
            },
            doOnSuccess = { address ->
                navigator.newRootChain(MainScreen)
            },
            doOnError = {
                navigator.newRootChain(MainScreen)
            }
        )
    }
}