package com.wallet.app.home.presentation.mvi

import androidx.lifecycle.viewModelScope
import com.wallet.app.home.interactors.HomeInteractor
import com.wallet.app.home.presentation.adapters.wrapper.TransactionWrapper
import com.wallet.app.presentation.extension.subscribe
import java.math.BigDecimal

internal class HomeViewModelImpl(
    private val interactor: HomeInteractor
) : HomeViewModel(HomeUiState()) {

    init {
        getBalance()
        getTransactions()
    }

    private fun getBalance() {
        viewModelScope.subscribe(
            { interactor.getBalance() },
            doOnSuccess = { balance ->
                updateUiState {
                    it.copy(
                        balance = balance,
                        btcNumber = BigDecimal(2), //todo api
                    )
                }
            },
            doOnError = { error ->
                updateUiState {
                    it.copy(
                        showExceptionMessage = "Error cant get balance ${error.localizedMessage}",
                        btcNumber = BigDecimal(0),
                        balance = BigDecimal(0)
                    )
                }
            }
        )
    }

    private fun getTransactions() {
        updateUiState { ui ->
            ui.copy(shimmerIsVisible = true)
        }
        viewModelScope.subscribe(
            { interactor.getTransactions() },
            doOnSuccess = { transactions ->
                updateUiState {
                    it.copy(
                        transactions = transactions.map { TransactionWrapper(it) },
                        shimmerIsVisible = false,
                        emptyStateVisible = transactions.isEmpty()
                    )
                }
            },
            doOnError = { error ->
                updateUiState {
                    it.copy(
                        showExceptionMessage = "Error cant get transactions ${error.localizedMessage}",
                        shimmerIsVisible = false,
                        emptyStateVisible = true
                    )
                }
            }
        )
    }
}