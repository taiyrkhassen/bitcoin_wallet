package com.wallet.app.home.presentation.mvi

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.wallet.app.home.databinding.FragmentHomeBinding
import com.wallet.app.home.di.HomeModule
import com.wallet.app.home.presentation.adapters.TransactionsDelegateAdapter
import com.wallet.app.home.presentation.adapters.wrapper.TransactionWrapper
import com.wallet.app.presentation.extension.toAmount
import com.wallet.app.presentation.ui.base.BaseUiStateFragment
import com.wallet.app.presentation.ui.components.CompositeAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseUiStateFragment<FragmentHomeBinding, HomeUiState, HomeViewModel>(
    HomeModule::class
) {
    companion object {
        fun newInstance() = HomeFragment()
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override val viewModel: HomeViewModel by viewModel()

    private val adapter by lazy {
        CompositeAdapter(
            TransactionsDelegateAdapter()
        )
    }

    override fun setupUi() = with(binding) {
        rvTransactions.adapter = adapter
    }

    @SuppressLint("SetTextI18n")
    override fun updateUiState(prevUiState: HomeUiState?, uiState: HomeUiState) = with(binding) {
        if (uiState.shimmerIsVisible) adapter.update(List(4) { TransactionWrapper(null) })
        else adapter.update(uiState.transactions)

        rvTransactions.isVisible = !uiState.emptyStateVisible
        vgEmpty.isVisible = uiState.emptyStateVisible

        tvBalanceBtc.text = uiState.btcNumber?.toAmount()

        tvBalanceUsd.isVisible = uiState.balance != null
        tvBalanceUsd.text = uiState.balance?.toAmount("USD")

        val errorMessage = uiState.showExceptionMessage

        if (!errorMessage.isNullOrEmpty()) Toast.makeText(
            requireContext(),
            errorMessage,
            Toast.LENGTH_LONG
        ).show()
    }

}