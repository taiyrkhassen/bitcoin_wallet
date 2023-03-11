package com.wallet.app.presentation.ui.components

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class CompositeAdapter(
    vararg adapters: DelegateAdapter<*, *>,
    var items: MutableList<Any> = mutableListOf(),
) : RecyclerView.Adapter<ViewHolder<Any>>() {

    @Suppress("UNCHECKED_CAST")
    private val adapters = adapters.map { it as DelegateAdapter<Any, ViewHolder<Any>> }

    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        return adapters.indexOfFirst { it.isForEntity(item) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        adapters[viewType].onCreateViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder<Any>, position: Int) =
        holder.bind(items, position)

    override fun onBindViewHolder(
        holder: ViewHolder<Any>,
        position: Int,
        payloads: MutableList<Any>,
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            payloads.forEachInstance<Any> { _, item ->
                (holder as? ViewHolder<Any>)?.render(item, true)
            }
        }
    }
    override fun getItemCount(): Int {
        return items.size
    }

    fun update(newItems: List<Any>) {
        if (newItems.isEmpty()) {
            items.clear()
            notifyDataSetChanged()
        } else {
            val diffResult = DiffUtil.calculateDiff(DiffUtilCallback(items, newItems))
            items = ArrayList(newItems)
            diffResult.dispatchUpdatesTo(this)
        }
    }

    fun update(item: Any) {
        findSameItem(item)?.let { sameItem ->
            val index = items.indexOf(sameItem)
            if (index != -1) {
                items[index] = item
                notifyItemChanged(index, item)
            }
        }
    }

    private inline fun <reified R> Iterable<*>.forEachInstance(action: (index: Int, item: R) -> Unit) {
        forEachIndexed { index, item -> if (item is R) action(index, item) }
    }

    private fun findSameItem(item: Any): Any? {
        val adapter = adapters.firstOrNull { it.isForEntity(item) }
        return items.firstOrNull {
            var areItemsTheSame = false
            val isSameAdapter = adapter?.isForEntity(it) ?: false
            if (isSameAdapter) {
                if (adapter?.differ?.areItemsTheSame(it, item) == true) {
                    areItemsTheSame = true
                }
            }
            areItemsTheSame
        }
    }

    inner class DiffUtilCallback(
        private val oldList: List<Any>,
        private val newList: List<Any>,
    ) : DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]

            val adapter = adapters.firstOrNull {
                it.isForEntity(oldItem) && it.isForEntity(newItem)
            }
            return adapter?.differ?.areItemsTheSame(oldItem, newItem) == true
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]

            val adapter = adapters.firstOrNull {
                it.isForEntity(oldItem) && it.isForEntity(newItem)
            }
            return adapter?.differ?.areContentsTheSame(oldItem, newItem) == true
        }

        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int) =
            newList[newItemPosition]
    }

    private fun DelegateAdapter<*, *>.isForEntity(any: Any) = entityClass.isInstance(any)
}
