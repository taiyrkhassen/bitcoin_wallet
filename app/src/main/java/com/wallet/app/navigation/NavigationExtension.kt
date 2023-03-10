package com.wallet.app.navigation

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.wallet.app.home.presentation.HomeFragment
import com.wallet.app.main.presentation.MainFragment
import com.wallet.app.presentation.navigation.HomeScreen
import com.wallet.app.presentation.navigation.MainScreen
import com.wallet.app.presentation.navigation.NavigationScreen
import com.wallet.app.presentation.navigation.SplashScreen
import com.wallet.app.presentation.navigation.TransferScreen
import com.wallet.app.splash.presentation.SplashFragment
import com.wallet.app.transfer.presentation.TransferFragment
import java.math.BigDecimal

fun NavigationScreen.toCiceroneScreen(): Screen? {
    return when (this) {
        is SplashScreen -> FragmentScreen { SplashFragment.newInstance() }
        is MainScreen -> FragmentScreen { MainFragment.newInstance() }
        is HomeScreen -> FragmentScreen { HomeFragment.newInstance(BigDecimal(0)) }
        is TransferScreen -> FragmentScreen { TransferFragment.newInstance() }
        else -> null
    }
}

fun Array<out NavigationScreen>.toCiceroneScreens(): List<Screen> {
    return mapNotNull { it.toCiceroneScreen() }
}