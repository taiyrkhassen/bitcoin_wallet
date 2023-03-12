package com.wallet.app.transfer.presentation.mvi

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.wallet.app.presentation.extension.toAmount
import com.wallet.app.presentation.ui.base.BaseUiStateFragment
import com.wallet.app.transfer.databinding.FragmentTransferBinding
import com.wallet.app.transfer.di.TransferModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.math.BigDecimal

class TransferFragment :
    BaseUiStateFragment<FragmentTransferBinding, TransferUiState, TransferViewModel>(
        TransferModule::class
    ) {

    companion object {
        fun newInstance() = TransferFragment()
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTransferBinding
        get() = FragmentTransferBinding::inflate

    override val viewModel: TransferViewModel by viewModel()

    override fun setupUi() = with(binding) {
        edAddress.doOnTextChanged { text, start, before, count ->
            viewModel.onAddressChanged(text?.toString())
        }
        edAmount.doOnTextChanged { text, start, before, count ->
            viewModel.onAmountChanged(text?.toString())
        }
        edNote.doOnTextChanged { text, start, before, count ->
            viewModel.onNoteChanged(text?.toString())
        }
        btnSend.setOnClickListener {
            viewModel.sendBitcoins() //todo api
        }
    }

    @SuppressLint("SetTextI18n")
    override fun updateUiState(prevUiState: TransferUiState?, uiState: TransferUiState) =
        with(binding) {
            inclBalance.shimmer.isVisible = uiState.shimmerIsVisible
            inclBalance.tvAmount.isVisible = !uiState.shimmerIsVisible

            inclBalance.tvAmount.text = uiState.balance?.toAmount()

            tvFee.isVisible = uiState.amountForFees != BigDecimal(0)
            tvFee.text =
                "Transaction fees: ${uiState.amountForFees}: ${uiState.transactionFee.toAmount()}"

            tilAddress.error = uiState.showExceptionMessage
            Toast.makeText(requireContext(), uiState.showExceptionMessage, Toast.LENGTH_LONG).show()

            btnSend.isEnabled = uiState.isButtonEnabled
        }

}