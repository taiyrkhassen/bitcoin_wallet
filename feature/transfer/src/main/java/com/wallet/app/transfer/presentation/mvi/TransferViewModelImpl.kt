package com.wallet.app.transfer.presentation.mvi

import androidx.lifecycle.viewModelScope
import com.wallet.app.presentation.extension.subscribe
import com.wallet.app.presentation.navigation.Navigator
import com.wallet.app.presentation.navigation.StatusPageScreen
import com.wallet.app.transfer.interactors.TransferInteractor
import kotlinx.coroutines.delay
import java.math.BigDecimal

class TransferViewModelImpl(
    private val navigator: Navigator,
    private val interactor: TransferInteractor
) : TransferViewModel(TransferUiState()) {

    init {
        getBalance()
    }

    private var newAmount: String? = null
    private var newAddress: String? = null
    private var newNote: String? = null

    private fun getBalance() {
        updateUiState {
            it.copy(
                shimmerIsVisible = true
            )
        }
        viewModelScope.subscribe(
            {
                delay(4000)
                interactor.getBalance()
            },
            doOnSuccess = { balance ->
                updateUiState {
                    it.copy(
                        balance = balance,
                        shimmerIsVisible = false
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
        viewModelScope.subscribe(
            { interactor.sendBitcoins() },
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

    override fun onAmountChanged(amount: String?) {
        this.newAmount = amount
        updateUiState {
            it.copy(
                amountForFees = amount?.toBigDecimal() ?: BigDecimal(0),
                transactionFee = interactor.getTransactionFee()
            )
        }
        checkButtonEnable()
    }

    override fun onAddressChanged(address: String?) {
        this.newAddress = address
        checkButtonEnable()
    }

    override fun onNoteChanged(note: String?) {
        this.newNote = note
    }

    private fun checkButtonEnable() {
        updateUiState {
            it.copy(
                isButtonEnabled = !newAmount.isNullOrEmpty() && !newAddress.isNullOrEmpty()
            )
        }
    }
}