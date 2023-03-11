package com.wallet.app.presentation.ui.components

import kotlin.reflect.KClass

open class DelegateAdapterDiffer<T : Any>(
    private val entityClass: KClass<out T>
) {

    open fun areItemsTheSame(oldItem: T, newItem: T) = oldItem == newItem

    open fun areContentsTheSame(oldItem: T, newItem: T) = false

}