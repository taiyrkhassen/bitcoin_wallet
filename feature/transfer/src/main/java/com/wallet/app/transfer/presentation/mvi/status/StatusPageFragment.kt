package com.wallet.app.transfer.presentation.mvi.status

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wallet.app.presentation.ui.base.BaseUiStateFragment
import com.wallet.app.transfer.databinding.FragmentStatusBinding
import com.wallet.app.transfer.di.TransferModule
import org.koin.androidx.viewmodel.ext.android.viewModel

class StatusPageFragment : BaseUiStateFragment<FragmentStatusBinding, StatusPageUiState, StatusPageViewModel>(
    TransferModule::class
) {
    companion object {
        fun newInstance() = StatusPageFragment()
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentStatusBinding
        get() = FragmentStatusBinding::inflate

    override val viewModel: StatusPageViewModel by viewModel()

    override fun setupUi() = with(binding) {
        btnSendMore.setOnClickListener {
            viewModel.onButtonSendClicked()
        }
        tvLink.setOnClickListener {
            //open browser
        }
    }

    override fun updateUiState(prevUiState: StatusPageUiState?, uiState: StatusPageUiState) = with(binding) {
        //nothing
    }

}