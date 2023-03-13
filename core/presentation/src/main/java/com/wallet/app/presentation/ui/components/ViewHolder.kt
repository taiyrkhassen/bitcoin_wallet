package com.wallet.app.presentation.ui.components

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class ViewHolder<T : Any>(
    itemView: View,
) : RecyclerView.ViewHolder(itemView) {

    protected var items: List<Any?>? = null
    protected var currentPosition: Int? = null

    @Suppress("UNCHECKED_CAST")
    fun bind(items: List<Any?>, position: Int) {
        this.items = items
        this.currentPosition = position
        (items[position] as? T)?.let { render(it, false) }
    }

    abstract fun render(entity: T, prevEntityUpdate: Boolean)
}