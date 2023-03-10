package com.wallet.app.presentation.listeners

/**
 * Обработчик нажатий "Back"
 */
interface OnBackPressedListener {
    /**
     * Возвращает true если нажатие обработано
     * */
    fun onBackPressed(): Boolean
}