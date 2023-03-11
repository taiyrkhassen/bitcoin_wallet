package com.wallet.app.home.presentation.mvi

import com.wallet.app.presentation.ui.base.BaseUiStateViewModel

abstract class HomeViewModel(
    initUi: HomeUiState
) : BaseUiStateViewModel<HomeUiState>(initUi)