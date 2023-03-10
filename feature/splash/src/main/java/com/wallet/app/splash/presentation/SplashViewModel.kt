package com.wallet.app.splash.presentation

import com.wallet.app.presentation.ui.base.BaseUiStateViewModel

abstract class SplashViewModel(
    initUi: SplashUiState
) : BaseUiStateViewModel<SplashUiState>(initUi)