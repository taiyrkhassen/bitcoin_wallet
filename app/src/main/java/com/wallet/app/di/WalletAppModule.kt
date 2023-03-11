package com.wallet.app.di

import android.app.Activity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ProcessLifecycleOwner
import com.google.gson.Gson
import com.wallet.app.presentation.MainActivity
import com.wallet.app.domain.di.BaseModule
import com.wallet.app.presentation.provider.ActivityProvider
import org.koin.core.module.Module
import org.koin.dsl.module

class WalletAppModule: BaseModule() {

    override val module: Module = module {
        factory { provideLifecycle() }
        factory { provideGson() }
        factory { provideActivityProvider() }
    }

    private fun provideLifecycle(): Lifecycle {
        return ProcessLifecycleOwner.get().lifecycle
    }

    private fun provideGson(): Gson {
        return Gson()
    }

    private fun provideActivityProvider() = object : ActivityProvider {
        override fun provideMainActivity(): Class<out Activity> {
            return MainActivity::class.java
        }
    }
}