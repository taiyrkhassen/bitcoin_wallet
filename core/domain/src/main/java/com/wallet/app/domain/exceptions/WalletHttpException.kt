package com.wallet.app.domain.exceptions

import okio.IOException

class WalletHttpException(
    val errorMessage: String
) : IOException()