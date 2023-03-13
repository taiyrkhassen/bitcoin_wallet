package com.wallet.app.splash.presentation

import com.wallet.app.domain.entities.uistates.SplashUi
import com.wallet.app.presentation.ui.base.BaseUiState

data class SplashUiState(
    val splashUi: SplashUi = SplashUi()
): BaseUiState()