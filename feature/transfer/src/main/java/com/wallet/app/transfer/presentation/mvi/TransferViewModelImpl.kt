package com.wallet.app.transfer.presentation.mvi

import androidx.lifecycle.viewModelScope
import com.wallet.app.presentation.extension.isLessThan
import com.wallet.app.presentation.extension.isZero
import com.wallet.app.presentation.extension.subscribe
import com.wallet.app.presentation.navigation.Navigator
import com.wallet.app.presentation.navigation.StatusPageScreen
import com.wallet.app.transfer.interactors.TransferInteractor
import java.math.BigDecimal

internal class TransferViewModelImpl(
    private val navigator: Navigator,
    private val interactor: TransferInteractor
) : TransferViewModel(TransferUiState()) {

    init {
        getBalance()
    }

    private var newAmount: BigDecimal? = null

    private var newAddress: String? = null
    private var newLabel: String? = null

    private var currentBalance: BigDecimal? = null

    private fun getBalance() {
        viewModelScope.subscribe(
            { interactor.getBalance() },
            doOnSuccess = { balance ->
                currentBalance = balance
                updateUiState {
                    it.copy(
                        balance = balance,
                        shimmerIsVisible = false,
                        transactionFee = interactor.getTransactionFee()
                    )
                }
            },
            doOnError = { error ->
                updateUiState {
                    it.copy(
                        showExceptionMessage = "Error cant get balance ${error.localizedMessage}",
                        shimmerIsVisible = true
                    )
                }
            }
        )
    }

    override fun sendBitcoins() {
        val amount = newAmount?.let { listOf(it.toString()) } ?: return
        val address = newAddress?.let { listOf(it) } ?: return

        viewModelScope.subscribe(
            { interactor.sendBitcoins(amount = amount, addresses = address, label = newLabel) },
            doOnSuccess = { balance ->
                navigator.navigateTo(StatusPageScreen)
            },
            doOnError = { error ->
                updateUiState {
                    it.copy(
                        showExceptionMessage = "Error cant send bitcoins ${error.localizedMessage}",
                    )
                }
            }
        )
    }

    override fun onAmountChanged(amount: String) {
        this.newAmount = amount.takeIf { it.isNotEmpty() }?.toBigDecimal() ?: BigDecimal.ZERO

        if (currentBalance?.isLessThan(newAmount) == true) {
            updateUiState {
                it.copy(
                    showExceptionMessage = "Not enough money",
                )
            }
            return
        }
        checkButtonEnable()
    }

    override fun onAddressChanged(address: String?) {
        this.newAddress = address
        checkButtonEnable()
    }

    override fun onNoteChanged(note: String?) {
        this.newLabel = note
    }

    private fun checkButtonEnable() {
        updateUiState {
            it.copy(
                isButtonEnabled = newAmount!=null && newAmount?.isZero() == false && !newAddress.isNullOrEmpty()
            )
        }
    }
}