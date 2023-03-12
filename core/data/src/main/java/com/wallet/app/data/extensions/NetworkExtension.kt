package com.wallet.app.data.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.telephony.TelephonyManager

private fun Context.connectivityManager(): ConnectivityManager {
    return getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
}

internal fun Context.isConnectedToNetwork(): Boolean {
    val cm = connectivityManager()
    val info = cm.activeNetworkInfo
    return info != null && info.isConnected
}

private fun ConnectivityManager.hasWifiConnection(): Boolean {
    return if (Build.VERSION.SDK_INT > 23) {
        getNetworkCapabilities(activeNetwork)?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            ?: false
    } else {
        activeNetworkInfo?.type == ConnectivityManager.TYPE_WIFI
    }
}

private fun ConnectivityManager.hasMobileConnection(): Boolean {
    return if (Build.VERSION.SDK_INT > 23) {
        getNetworkCapabilities(activeNetwork)?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
            ?: false
    } else {
        activeNetworkInfo?.type == ConnectivityManager.TYPE_MOBILE
    }
}


internal fun Context.isConnectedFast(): Boolean {
    val cm = connectivityManager()
    return when {
        cm.hasWifiConnection() -> true
        cm.hasMobileConnection() -> {
            val type = cm.activeNetworkInfo?.subtype ?: TelephonyManager.NETWORK_TYPE_UNKNOWN
            isMobileNetworkFast(type)
        }

        else -> false
    }
}

private fun isMobileNetworkFast(type: Int): Boolean {
    return when (type) {
        TelephonyManager.NETWORK_TYPE_1xRTT -> false // ~ 50-100 kbps
        TelephonyManager.NETWORK_TYPE_CDMA -> false // ~ 14-64 kbps
        TelephonyManager.NETWORK_TYPE_EDGE -> false // ~ 50-100 kbps
        TelephonyManager.NETWORK_TYPE_EVDO_0 -> true // ~ 400-1000 kbps
        TelephonyManager.NETWORK_TYPE_EVDO_A -> true // ~ 600-1400 kbps
        TelephonyManager.NETWORK_TYPE_GPRS -> false // ~ 100 kbps
        TelephonyManager.NETWORK_TYPE_HSDPA -> true // ~ 2-14 Mbps
        TelephonyManager.NETWORK_TYPE_HSPA -> true // ~ 700-1700 kbps
        TelephonyManager.NETWORK_TYPE_HSUPA -> true // ~ 1-23 Mbps
        TelephonyManager.NETWORK_TYPE_UMTS -> true // ~ 400-7000 kbps
        TelephonyManager.NETWORK_TYPE_EHRPD -> true // ~ 1-2 Mbps
        TelephonyManager.NETWORK_TYPE_EVDO_B -> true // ~ 5 Mbps
        TelephonyManager.NETWORK_TYPE_HSPAP -> true // ~ 10-20 Mbps
        TelephonyManager.NETWORK_TYPE_IDEN -> false // ~25 kbps
        TelephonyManager.NETWORK_TYPE_LTE -> true // ~ 10+ Mbps
        TelephonyManager.NETWORK_TYPE_UNKNOWN -> false
        else -> false
    }
}

