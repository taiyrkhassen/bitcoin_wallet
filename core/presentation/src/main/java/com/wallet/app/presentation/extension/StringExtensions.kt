package com.wallet.app.presentation.extension

import androidx.core.util.PatternsCompat

fun String.isValidEmail(): Boolean = !isNullOrBlank() && PatternsCompat.EMAIL_ADDRESS.matcher(trim()).matches()