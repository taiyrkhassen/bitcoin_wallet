package com.wallet.app

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.wallet.app.data.di.RepositoryModule
import com.wallet.app.data.network.di.NetworkModule
import com.wallet.app.di.MainActModule
import com.wallet.app.di.NavigationModule
import com.wallet.app.di.WalletAppModule
import com.wallet.app.home.di.HomeModule
import com.wallet.app.main.di.MainModule
import com.wallet.app.splash.di.SplashModule
import com.wallet.app.transfer.di.TransferModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.module.Module
import timber.log.Timber

class WalletApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
        initTimber()
        initNotificationChannels()
    }

    private fun provideModules(): List<Module> {
        return listOf(
            MainActModule().module,
            WalletAppModule().module,
            NetworkModule().module,
            MainModule().module,
            RepositoryModule().module,
            SplashModule().module,
            NavigationModule().module,
            HomeModule().module,
            TransferModule().module
        )
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@WalletApplication)
            modules(provideModules())
        }
    }

    private fun initNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // messages notification channel
            createNotificationChannel(
                id = "message_notification",
                name = "Messages",
                description = "Important messages goes into this channel",
                NotificationManager.IMPORTANCE_HIGH
            )
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private fun createNotificationChannel(
        id: String,
        name: String,
        description: String,
        importance: Int
    ) {
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(id, name, importance).apply {
            this.description = description
        }
        manager.createNotificationChannel(channel)
    }
}