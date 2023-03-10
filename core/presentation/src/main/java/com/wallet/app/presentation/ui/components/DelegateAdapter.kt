package com.wallet.app.presentation.ui.components

import android.os.Bundle
import android.view.ViewGroup

@Suppress("UNCHECKED_CAST")
abstract class DelegateAdapter<T, VH : ViewHolder<T>> {
    abstract fun onCreateViewHolder(parent: ViewGroup): VH

    abstract fun isForEntity(any: Any?): Boolean

    fun areItemsTheSame(oldItem: Any?, newItem: Any?): Boolean {
        val oldValue = oldItem as? T
        val newValue = newItem as? T
        if (oldValue == null && newValue == null) {
            return true
        }
        if (oldItem != null && newItem != null && oldItem::class.java != newItem::class.java) {
            return false
        }
        return areTypedItemsTheSame(oldValue, newValue)
    }

    abstract fun areTypedItemsTheSame(oldItem: T?, newItem: T?): Boolean

    fun areContentsTheSame(oldItem: Any?, newItem: Any?): Boolean {
        val oldValue = oldItem as? T
        val newValue = newItem as? T

        if (oldValue == null && newValue == null) {
            return true
        }
        return areTypedContentsTheSame(oldValue, newValue)
    }

    abstract fun areTypedContentsTheSame(oldItem: T?, newItem: T?): Boolean

    fun getChangePayload(oldItem: Any?, newItem: Any?): Bundle {
        val oldValue = oldItem as? T
        val newValue = newItem as? T
        return getTypedChangePayload(oldValue, newValue)
    }

    abstract fun getTypedChangePayload(oldItem: T?, newItem: T?): Bundle
}