package com.wallet.app.main.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wallet.app.home.presentation.mvi.HomeFragment
import com.wallet.app.main.R
import com.wallet.app.main.databinding.FragmentMainBinding
import com.wallet.app.main.di.MainModule
import com.wallet.app.presentation.listeners.OnBackPressedListener
import com.wallet.app.presentation.ui.base.BaseUiStateFragment
import com.wallet.app.transfer.presentation.mvi.TransferFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.math.BigDecimal

class MainFragment : BaseUiStateFragment<FragmentMainBinding, MainUiState, MainViewModel>(
    MainModule::class,
    R.layout.fragment_main
), OnBackPressedListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    override val viewModel: MainViewModel by viewModel()

    private val tabIds = listOf(
        R.id.HomeButtonNavigation,
        R.id.TransferButtonNavigation
    )

    override fun setupUi() = with(binding) {
        mainTabbar.setOnNavigationItemSelectedListener {
            showFragment(it.itemId)
            return@setOnNavigationItemSelectedListener true
        }
        if (childFragmentManager.fragments.isEmpty()) {
            mainTabbar.selectedItemId = R.id.HomeButtonNavigation
        }
    }

    override fun onBackPressed(): Boolean = with(binding) {
        if (mainTabbar.selectedItemId != R.id.HomeButtonNavigation) {
            mainTabbar.selectedItemId = R.id.HomeButtonNavigation
            return true
        }
        return false
    }

    private fun showFragment(tabId: Int) {
        val targetFragment = when (tabId) {
            R.id.HomeButtonNavigation -> HomeFragment.newInstance()
            R.id.TransferButtonNavigation -> TransferFragment.newInstance()
            else -> HomeFragment.newInstance()
        }
        val previousFragment = tabIds
            .mapNotNull { childFragmentManager.findFragmentByTag(it.toString()) }
            .firstOrNull { it.isVisible }

        val fragmentTransaction = childFragmentManager.beginTransaction()
        previousFragment?.let { fragmentTransaction.hide(previousFragment) }

        fragmentTransaction.add(
            R.id.flContainer,
            targetFragment,
            tabId.toString()
        )

        fragmentTransaction.commit()
    }

    override fun updateUiState(prevUiState: MainUiState?, uiState: MainUiState) {
        //nothing
    }

}