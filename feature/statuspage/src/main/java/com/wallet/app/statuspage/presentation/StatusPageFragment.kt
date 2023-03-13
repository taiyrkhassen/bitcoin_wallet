package com.wallet.app.statuspage.presentation

import android.content.Intent
import android.net.Uri
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
        private const val ARG_TX_ID = "arg_tx_id"
        fun newInstance(txId: String) = StatusPageFragment().apply {
            ARG_TX_ID to txId
        }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentStatusBinding
        get() = FragmentStatusBinding::inflate

    override val viewModel: StatusPageViewModel by viewModel()

    override fun setupUi() = with(binding) {
        btnSendMore.setOnClickListener {
            viewModel.onButtonSendClicked()
        }
        val txiD = requireArguments().getString(ARG_TX_ID)
        tvLink.text = "Your transaction ID is: $txiD"
        tvLink.setOnClickListener {
            val url = "https://blockstream.info/tx/$txiD"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }

    override fun updateUiState(prevUiState: StatusPageUiState?, uiState: StatusPageUiState) =
        with(binding) {
            //nothing
        }

}