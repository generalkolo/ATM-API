package com.semanienterprise.atm.operations.debit_amount

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WithdrawalAmountViewModel : ViewModel() {

    private val _navigateToOthers = MutableLiveData<Boolean>()
    val navigateToOthers: LiveData<Boolean>
        get() = _navigateToOthers

    fun navigateToOthersFragment() {
        _navigateToOthers.value = true
    }

    fun navigateToOthersFragmentCompleted() {
        _navigateToOthers.value = false
    }

    private val _navigateToCardFragment = MutableLiveData<Boolean>()
    val navigateToCardFragment: LiveData<Boolean>
        get() = _navigateToCardFragment

    fun navigateToCardFragment() {
        _navigateToCardFragment.value = true
    }

    fun navigateToCardFragmentComplete() {
        _navigateToCardFragment.value = false
    }

    private val _navigateToDispenseFragment = MutableLiveData<String>()
    val navigateToDispenseFragment: LiveData<String>
        get() = _navigateToDispenseFragment

    fun navigateToDispenseFragment(debitAmount: String) {
        _navigateToDispenseFragment.value = debitAmount
    }

    fun navigateToDispenseFragmentCompleted() {
        _navigateToDispenseFragment.value = null
    }
}