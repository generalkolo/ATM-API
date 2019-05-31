package com.semanienterprise.atm.operations.account_selection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semanienterprise.atm.network.AtmCard
import com.semanienterprise.atm.operations.operation_selector.OperationsFragment.Companion.BALANCE_ENQUIRY
import com.semanienterprise.atm.operations.operation_selector.OperationsFragment.Companion.WITHDRAWAL

class AccountSelectionViewModel(val selection: String, val atmCard: AtmCard) : ViewModel() {

    private val _navigateToCardFragment = MutableLiveData<Boolean>()
    val navigateToCardFragment: LiveData<Boolean>
        get() = _navigateToCardFragment

    fun navigateToCardFragment() {
        _navigateToCardFragment.value = true
    }

    fun navigateToCardFragmentComplete() {
        _navigateToCardFragment.value = false
    }

    private val _navigateToBalanceFragment = MutableLiveData<Boolean>()
    val navigateToBalanceFragment: LiveData<Boolean>
        get() = _navigateToBalanceFragment

    fun navigateToDestinationCurrentAccount() {
        when (selection) {
            BALANCE_ENQUIRY -> {
                _navigateToBalanceFragment.value = true
            }
            WITHDRAWAL -> {
                _navigateToWithdrawalFragment.value = true
            }
        }
    }

    fun navigateToBalanceFragmentComplete() {
        _navigateToBalanceFragment.value = false
    }

    private val _navigateToWithdrawalFragment = MutableLiveData<Boolean>()
    val navigateToWithdrawalFragment: LiveData<Boolean>
        get() = _navigateToWithdrawalFragment

    fun navigateToDestinationSavingsAccount() {
        when (selection) {
            BALANCE_ENQUIRY -> {
                _navigateToBalanceFragment.value = true
            }
            WITHDRAWAL -> {
                _navigateToWithdrawalFragment.value = true
            }
        }
    }

    fun navigateToWithdrawalComplete() {
        _navigateToWithdrawalFragment.value = false
    }
}