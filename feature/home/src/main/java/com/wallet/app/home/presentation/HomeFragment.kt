package com.wallet.app.home.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.wallet.app.home.databinding.FragmentHomeBinding
import com.wallet.app.home.di.HomeModule
import com.wallet.app.presentation.ui.base.BaseUiStateFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.math.BigDecimal

class HomeFragment : BaseUiStateFragment<FragmentHomeBinding, HomeUiState, HomeViewModel>(
    HomeModule::class
) {

    companion object {
        private const val ARG_BALANCE = "arg_balance"

        fun newInstance(balance: BigDecimal) = HomeFragment().apply {
            arguments = bundleOf(ARG_BALANCE to balance)
        }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override val viewModel: HomeViewModel by viewModel()

    override fun setupUi() = with(binding) {

    }

    override fun updateUiState(prevUiState: HomeUiState?, uiState: HomeUiState) {
        //nothing
    }

}