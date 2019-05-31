package com.semanienterprise.atm.balance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BalanceFragmentViewModel : ViewModel() {
    private val _navigateBackToCardFragment = MutableLiveData<Boolean>()
    val navigateBackToCardFragment: LiveData<Boolean>
        get() = _navigateBackToCardFragment

    private val _navigateBackToPinFragment = MutableLiveData<Boolean>()
    val navigateBackToPinFragment: LiveData<Boolean>
        get() = _navigateBackToPinFragment

    fun navigateBackToCardFragment() {
        _navigateBackToCardFragment.value = true
    }

    fun navigateBackToCardFragmentCompleted() {
        _navigateBackToCardFragment.value = false
    }

    fun navigateToPinFragment() {
        _navigateBackToPinFragment.value = true
    }

    fun navigateToPinFragmentCompleted() {
        _navigateBackToPinFragment.value = true
    }
}