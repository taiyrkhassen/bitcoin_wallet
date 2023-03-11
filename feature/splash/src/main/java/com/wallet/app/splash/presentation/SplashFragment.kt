package com.wallet.app.splash.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wallet.app.presentation.ui.base.BaseUiStateFragment
import com.wallet.app.splash.databinding.FragmentSplashBinding
import com.wallet.app.splash.di.SplashModule
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseUiStateFragment<FragmentSplashBinding, SplashUiState, SplashViewModel>(
    SplashModule::class
) {
    companion object {
        fun newInstance() = SplashFragment()
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSplashBinding
        get() = FragmentSplashBinding::inflate

    override val viewModel: SplashViewModel by viewModel()

    override fun setupUi() {
        //do nothing here
    }

    override fun updateUiState(prevUiState: SplashUiState?, uiState: SplashUiState) {
        //do nothing here
    }
}