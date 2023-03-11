package com.wallet.app.transfer.presentation.mvi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.wallet.app.presentation.extension.toAmount
import com.wallet.app.presentation.ui.base.BaseUiStateFragment
import com.wallet.app.transfer.databinding.FragmentTransferBinding
import com.wallet.app.transfer.di.TransferModule
import org.koin.androidx.viewmodel.ext.android.viewModel

class TransferFragment : BaseUiStateFragment<FragmentTransferBinding, TransferUiState, TransferViewModel>(
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

    override fun updateUiState(prevUiState: TransferUiState?, uiState: TransferUiState) = with(binding) {
        inclBalance.shimmer.isVisible = uiState.shimmerIsVisible
        inclBalance.tvAmount.text = uiState.balance.toAmount()
        tvFee.text = "Transaction fees: 0.0008 BTC: ${uiState.transactionFee.toAmount()}"
        tvMinMax.text = "Min: ${uiState.minSum} - Max: ${uiState.minSum}"

        tilAddress.error = uiState.showExceptionMessage
        btnSend.isEnabled = uiState.isButtonEnabled
    }

}