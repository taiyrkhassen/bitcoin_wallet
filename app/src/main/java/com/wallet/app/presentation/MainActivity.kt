package com.wallet.app.presentation

import android.os.Bundle
import android.view.LayoutInflater
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.wallet.app.databinding.ActivityMainBinding
import com.wallet.app.di.MainActModule
import com.wallet.app.presentation.extension.setStatusBarTransparent
import com.wallet.app.presentation.ui.base.BaseActivity
import org.koin.android.ext.android.get

class MainActivity : BaseActivity<ActivityMainBinding, MainActUiState, MainActViewModel>(
    MainActModule::class
) {
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    private val navigatorHolder: NavigatorHolder = get()

    override val viewModel: MainActViewModel by lazy {
        scope.get()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setStatusBarTransparent(binding.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(AppNavigator(this, com.wallet.app.R.id.flActivityContainer))
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun updateUiState(prevUiState: MainActUiState?, uiState: MainActUiState) {
        //nohing
    }

}