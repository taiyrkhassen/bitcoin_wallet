package com.wallet.app.navigation

import android.content.Context
import com.github.terrakok.cicerone.Screen
import com.wallet.app.presentation.navigation.CustomRouter
import com.wallet.app.presentation.navigation.NavigationScreen
import com.wallet.app.presentation.navigation.NavigationScreenForResult
import com.wallet.app.presentation.navigation.Navigator

class NavigatorImpl(
    private val context: Context,
    private val router: CustomRouter
): Navigator {
    private var rootScreen: Screen? = null

    override fun newRootScreen(screen: NavigationScreen) {
        screen.toCiceroneScreen()?.let { router.newRootScreen(it) }
    }

    override fun newChainUponRoot(vararg screens: NavigationScreen) {
        val ciceroneScreens = screens.toCiceroneScreens()
            .filter { it.screenKey != rootScreen?.screenKey }

        rootScreen?.let { router.backTo(it) }
        if (ciceroneScreens.isNotEmpty()) {
            router.newChain(*ciceroneScreens.toTypedArray())
        }
    }

    override fun newRootChain(vararg screens: NavigationScreen) {
        val ciceroneScreens = screens.toCiceroneScreens()
        if (ciceroneScreens.isNotEmpty()) {
            router.newRootChain(*ciceroneScreens.toTypedArray())
        }
    }

    override fun replaceScreen(screen: NavigationScreen) {
        screen.toCiceroneScreen()?.let { router.replaceScreen(it) }
    }

    override fun navigateTo(screen: NavigationScreen) {
        screen.toCiceroneScreen()?.let { router.navigateTo(it) }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <R : Any> navigateToForResult(
        screen: NavigationScreenForResult,
        onResult: (R) -> Unit
    ) {
        screen.toCiceroneScreen()?.let {
            router.setResultListener(screen.resultKey) { result -> onResult(result as R) }
            router.navigateTo(it)
        }
    }

    override fun navigateBack() {
        router.exit()
    }

    override fun navigateBackWithResult(resultKey: String, result: Any) {
        router.sendResult(resultKey, result)
        router.exit()
    }

    override fun exit() {
        router.exit()
    }
}