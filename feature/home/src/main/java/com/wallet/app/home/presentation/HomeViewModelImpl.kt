package com.wallet.app.home.presentation

import com.wallet.app.presentation.navigation.Navigator

class HomeViewModelImpl(
    private val navigator: Navigator
) : HomeViewModel(HomeUiState()) {

}