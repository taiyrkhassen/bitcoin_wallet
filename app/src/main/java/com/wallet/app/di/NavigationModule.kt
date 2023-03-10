package com.wallet.app.di

import android.content.Context
import com.github.terrakok.cicerone.BaseRouter
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.wallet.app.domain.di.BaseModule
import com.wallet.app.navigation.NavigatorImpl
import com.wallet.app.presentation.navigation.CustomRouter
import com.wallet.app.presentation.navigation.Navigator
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

class NavigationModule : BaseModule() {
    override val module: Module = module {
        single<BaseRouter> { CustomRouter() }
        single { provideRouter(get()) }
        single<Cicerone<*>> { Cicerone.create(get()) }
        single { provideNavigatorHolder(get()) }
        single { provideNavigator(androidContext(), get()) }
    }

    private fun provideRouter(router: BaseRouter): CustomRouter {
        return router as CustomRouter
    }

    private fun provideNavigatorHolder(cicerone: Cicerone<*>): NavigatorHolder {
        return cicerone.getNavigatorHolder()
    }

    private fun provideNavigator(
        context: Context,
        router: CustomRouter
    ): Navigator {
        return NavigatorImpl(context, router)
    }
}