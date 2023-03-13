package com.wallet.app.presentation.extension

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import timber.log.Timber

fun <T> tryOrNull(function: () -> T): T? = try {
    function()
} catch (e: Exception) {
    Timber.e(e)
    null
}

fun Window.setStatusBarTransparent(view: View) {
    this.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    this.statusBarColor = Color.TRANSPARENT
    WindowCompat.setDecorFitsSystemWindows(this, false)
    ViewCompat.setOnApplyWindowInsetsListener(view) { root, windowInset ->
        val inset = windowInset.getInsets(WindowInsetsCompat.Type.systemBars())
        root.updateLayoutParams<ViewGroup.MarginLayoutParams> {
            leftMargin = inset.left
            bottomMargin = inset.bottom
            rightMargin = inset.right
            topMargin = 0
        }
        WindowInsetsCompat.CONSUMED
    }
}
