package com.wallet.app.presentation

import com.wallet.app.presentation.ui.base.BaseUiStateViewModel

abstract class MainActViewModel(
    initUi: MainActUiState
) : BaseUiStateViewModel<MainActUiState>(initUi)