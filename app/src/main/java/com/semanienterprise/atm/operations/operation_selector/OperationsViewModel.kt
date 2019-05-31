package com.semanienterprise.atm.operations.operation_selector

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semanienterprise.atm.network.AtmCard

class OperationsViewModel(val atmCard: AtmCard) : ViewModel() {
    private val _navigateToAccountSelection = MutableLiveData<Boolean>()
    val navigateToAccountSelection: LiveData<Boolean>
        get() = _navigateToAccountSelection

    fun navigateToAccountSelectionComplete() {
        _navigateToAccountSelection.value = false
    }

    fun onNavigateToAccountSelectionToWithdrawal() {
        _navigateToAccountSelection.value = true
    }

    fun onNavigateToAccountSelectionForBalance() {
        _navigateToBalanceFragment.value = true
    }

    private val _navigateBackToCardFragment = MutableLiveData<Boolean>()
    val navigateBackToCardFragment: LiveData<Boolean>
        get() = _navigateBackToCardFragment

    fun navigateBackToCardFragmentComplete() {
        _navigateBackToCardFragment.value = false
    }

    fun navigateToCardFragment() {
        _navigateBackToCardFragment.value = true
    }

    private val _navigateToBalanceFragment = MutableLiveData<Boolean>()
    val navigateToBalanceFragment: LiveData<Boolean>
        get() = _navigateToBalanceFragment

    fun navigateToBalanceFragmentComplete() {
        _navigateToBalanceFragment.value = false
    }
}