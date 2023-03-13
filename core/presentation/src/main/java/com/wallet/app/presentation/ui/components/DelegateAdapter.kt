package com.wallet.app.presentation.ui.components

import android.view.ViewGroup
import kotlin.reflect.KClass

abstract class DelegateAdapter<T : Any, VH : ViewHolder<T>>(
    val entityClass: KClass<out T>
) {
    abstract fun onCreateViewHolder(parent: ViewGroup): VH

    open val differ = DelegateAdapterDiffer<T>(entityClass)

}