package com.wallet.app.presentation.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

abstract class BaseUiStateViewModel<UI : BaseUiState>(initUi: UI) : ViewModel() {

    private val _uiState = MutableStateFlow(initUi)
    val uiState = _uiState as StateFlow<UI>

    protected fun updateUiState(update: (currentModel: UI) -> UI) = _uiState.update { update(it) }

}
