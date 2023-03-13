package com.wallet.app.presentation.navigation

sealed class NavigationScreen

sealed class NavigationScreenForResult(
    open val resultKey: String
) : NavigationScreen()

object SplashScreen : NavigationScreen()

object MainScreen : NavigationScreen()

data class StatusPageScreen(val txId: String) : NavigationScreen()

object HomeScreen : NavigationScreen()

object TransferScreen : NavigationScreen()

