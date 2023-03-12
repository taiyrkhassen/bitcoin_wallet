package com.wallet.app.home.presentation.mvi

import androidx.lifecycle.viewModelScope
import com.wallet.app.home.interactors.HomeInteractor
import com.wallet.app.home.presentation.adapters.wrapper.TransactionWrapper
import com.wallet.app.presentation.extension.subscribe

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
                        balance = null, //todo api
                        btcNumber = balance,
                    )
                }
            },
            doOnError = { error ->
                updateUiState {
                    it.copy(
                        showExceptionMessage = "Error cant get balance ${error.localizedMessage}"
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