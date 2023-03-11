package com.wallet.app.presentation.navigation

import android.os.Parcelable
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class NavigationScreen : Parcelable

@Parcelize
sealed class NavigationScreenForResult(
    open val resultKey: String
) : NavigationScreen()

object SplashScreen : NavigationScreen()

object MainScreen : NavigationScreen()

object HomeScreen : NavigationScreen()

object TransferScreen : NavigationScreen()

