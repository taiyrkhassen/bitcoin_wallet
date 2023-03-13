package com.wallet.app.presentation.provider

import android.app.Activity

interface ActivityProvider {
    fun provideMainActivity(): Class<out Activity>
}