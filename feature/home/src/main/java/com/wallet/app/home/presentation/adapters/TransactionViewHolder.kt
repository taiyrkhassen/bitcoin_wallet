package com.wallet.app.home.presentation.adapters

import android.annotation.SuppressLint
import android.view.View
import androidx.core.content.ContextCompat
import com.wallet.app.domain.entities.Transaction
import com.wallet.app.home.R
import com.wallet.app.home.databinding.ItemTransactionBinding
import com.wallet.app.home.presentation.adapters.wrapper.TransactionWrapper
import com.wallet.app.presentation.extension.toFormattedString
import com.wallet.app.presentation.ui.components.ViewHolder
import java.math.RoundingMode

class TransactionViewHolder(
    private val binding: ItemTransactionBinding,
) : ViewHolder<TransactionWrapper>(binding.root) {

    @SuppressLint("SetTextI18n")
    override fun render(entity: TransactionWrapper, prevEntityUpdate: Boolean) =
        with(binding) {
            if (entity.item == null) {
                startShimmer()
                return
            }
            stopShimmer()
            val item = entity.item
            tvTitleStatus.text = "Bitcoin"
            sivArrowStatus.setImageDrawable(ContextCompat.getDrawable(root.context, item.getArrow()))
            tvAmount.text = "${item.getSign()}${item.amount.setScale(5, RoundingMode.DOWN)} BTC"
            tvDate.text = item.date.toFormattedString("dd-MM-yyyy HH:mm:ss:aa")
        }

    private fun Transaction.getSign(): String =
        if (this.status == Transaction.TransactionStatus.RECEIVED) "+"
        else "-"

    private fun Transaction.getArrow(): Int =
        if (this.status == Transaction.TransactionStatus.RECEIVED) R.drawable.ic_arrow_down
        else R.drawable.ic_arrow_up

    private fun startShimmer() = with(binding) {
        shimmer.root.visibility = View.VISIBLE
        clContent.visibility = View.GONE
    }

    private fun stopShimmer() = with(binding) {
        shimmer.root.visibility = View.GONE
        clContent.visibility = View.VISIBLE
    }
}