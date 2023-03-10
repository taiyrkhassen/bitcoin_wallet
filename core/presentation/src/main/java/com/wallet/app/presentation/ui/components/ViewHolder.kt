package com.wallet.app.presentation.ui.components

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class ViewHolder<T>(
    itemView: View,
) : RecyclerView.ViewHolder(itemView) {

    protected var items: List<Any?>? = null
    protected var currentPosition: Int? = null

    @Suppress("UNCHECKED_CAST")
    fun bind(items: List<Any?>, position: Int) {
        this.items = items
        this.currentPosition = position
        render(items[position] as? T)
    }

    fun bind(entity: T?) {
        render(entity)
    }

    protected abstract fun render(entity: T?)

    open fun applyChanges(bundle: Bundle) {

    }
}