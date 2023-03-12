package com.wallet.app.domain.exceptions

import java.io.IOException

class NoNetworkConnectionException(
    val url: String? = null
) : IOException() {

    override val message: String
        get() = "No network connection" + if (url != null) ", request: $url" else ""
}