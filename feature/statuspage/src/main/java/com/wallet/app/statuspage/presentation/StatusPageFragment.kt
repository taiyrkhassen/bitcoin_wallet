package com.wallet.app.statuspage.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wallet.app.presentation.ui.base.BaseUiStateFragment
import com.wallet.app.statuspage.databinding.FragmentStatusBinding
import com.wallet.app.statuspage.di.StatusModule
import org.koin.androidx.viewmodel.ext.android.viewModel

class StatusPageFragment :
    BaseUiStateFragment<FragmentStatusBinding, StatusPageUiState, StatusPageViewModel>(
        StatusModule::class
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

    override fun updateUiState(prevUiState: StatusPageUiState?, uiState: StatusPageUiState) =
        with(binding) {
            //nothing
        }

}