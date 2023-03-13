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
            tilAmount.error = null
            viewModel.onAmountChanged(text?.toString().orEmpty())
        }
        edNote.doOnTextChanged { text, start, before, count ->
            viewModel.onNoteChanged(text?.toString())
        }
        btnSend.setOnClickListener {
            viewModel.sendBitcoins()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun updateUiState(prevUiState: TransferUiState?, uiState: TransferUiState) =
        with(binding) {

            inclBalance.shimmer.isVisible = uiState.shimmerIsVisible
            inclBalance.tvAmount.isVisible = !uiState.shimmerIsVisible
            inclBalance.tvAmount.text = uiState.balance.toAmount()

            tvFee.text = "Transaction fees: ${uiState.transactionFee.toAmount()}"

            val errorMessage = uiState.showExceptionMessage


            tilAmount.error = errorMessage

            if (!errorMessage.isNullOrEmpty()) Toast.makeText(
                requireContext(),
                errorMessage,
                Toast.LENGTH_LONG
            ).show()
            btnSend.isEnabled = uiState.isButtonEnabled
        }

}