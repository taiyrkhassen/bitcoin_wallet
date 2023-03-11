package com.wallet.app.presentation.ui.components

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.max
import kotlin.math.min

class CompositeAdapter(
    vararg adapters: DelegateAdapter<*, *>,
    var items: MutableList<Any?> = mutableListOf(),
) : RecyclerView.Adapter<ViewHolder<*>>() {

    private val adapters = adapters.toList()

    override fun getItemViewType(position: Int): Int {
        return adapters.indexOfFirst { it.isForEntity(items[position]) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        adapters[viewType].onCreateViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder<*>, position: Int) =
        holder.bind(items, position)

    override fun onBindViewHolder(
        holder: ViewHolder<*>,
        position: Int,
        payloads: MutableList<Any>,
    ) {
        val payloadBundle = payloads.getOrNull(0) as? Bundle
        if (payloadBundle?.isEmpty == false) {
            holder.applyChanges(payloadBundle)
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun update(newItems: List<Any?>) {
        val diffResult = DiffUtil.calculateDiff(DiffUtilCallback(items, newItems))
        items = ArrayList(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    fun addOrUpdate(item: Any) {
        val adapter = adapters.firstOrNull { it.isForEntity(item) }
        val sameItem = items.firstOrNull {
            val isSameAdapter = adapter?.isForEntity(it) ?: false
            val areItemsTheSame = adapter?.areItemsTheSame(it, item) ?: false
            isSameAdapter && areItemsTheSame
        }
        if (sameItem == null) {
            items.add(item)
            notifyItemInserted(items.size - 1)
        } else {
            val index = items.indexOf(sameItem)
            if (index != -1) {
                items[index] = item
                notifyItemChanged(index, adapter?.getChangePayload(sameItem, item))
            }
        }
    }

    fun addAll(newItems: List<Any?>) {
        val newList = arrayListOf<Any?>().apply {
            addAll(items)
            addAll(newItems)
        }
        val diffResult = DiffUtil.calculateDiff(DiffUtilCallback(items, newList))
        items = newList
        diffResult.dispatchUpdatesTo(this)
    }

    fun getItemByPosition(position: Int) = if (itemCount > 0) {
        items[max(0, min(position, itemCount - 1))]
    } else {
        null
    }

    inner class DiffUtilCallback(
        private val oldList: List<Any?>,
        private val newList: List<Any?>,
    ) : DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]

            val adapter = adapters.firstOrNull {
                it.isForEntity(oldItem) && it.isForEntity(newItem)
            }
            return adapter?.areItemsTheSame(oldItem, newItem) == true
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]

            val adapter = adapters.firstOrNull {
                it.isForEntity(oldItem) && it.isForEntity(newItem)
            }
            return adapter?.areContentsTheSame(oldItem, newItem) == true
        }

        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]

            val adapter = adapters.firstOrNull {
                it.isForEntity(oldItem) && it.isForEntity(newItem)
            }
            return adapter?.getChangePayload(oldItem, newItem)
        }
    }
}