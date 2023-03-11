package com.wallet.app.home.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wallet.app.home.databinding.ItemTransactionBinding
import com.wallet.app.home.presentation.adapters.wrapper.TransactionWrapper
import com.wallet.app.presentation.ui.components.DelegateAdapter

class TransactionsDelegateAdapter : DelegateAdapter<TransactionWrapper, TransactionViewHolder>(TransactionWrapper::class) {
    override fun onCreateViewHolder(parent: ViewGroup): TransactionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTransactionBinding.inflate(inflater, parent, false)
        return TransactionViewHolder(binding)
    }
}