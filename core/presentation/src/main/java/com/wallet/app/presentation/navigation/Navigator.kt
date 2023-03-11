package com.wallet.app.presentation.navigation

interface Navigator {


    fun newRootScreen(screen: NavigationScreen)
    fun newChainUponRoot(vararg screens: NavigationScreen)
    fun newRootChain(vararg screens: NavigationScreen)
    fun replaceScreen(screen: NavigationScreen)
    fun navigateTo(screen: NavigationScreen)

    fun <R : Any> navigateToForResult(screen: NavigationScreenForResult, onResult: (R) -> Unit)

    fun navigateBack()
    fun navigateBackWithResult(resultKey: String, result: Any)
    fun exit()
}