package com.wallet.app.transfer.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
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

    }

    override fun updateUiState(prevUiState: TransferUiState?, uiState: TransferUiState) {
        //nothing
    }

}