package com.wallet.app.presentation.extension

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.toFormattedString(pattern: String = "yyyy-MM-dd"): String? = tryOrNull {
    SimpleDateFormat(pattern, Locale.getDefault()).format(this)
}