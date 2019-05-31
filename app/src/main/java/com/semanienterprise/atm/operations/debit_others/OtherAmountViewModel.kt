package com.semanienterprise.atm.operations.debit_others

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OtherAmountViewModel : ViewModel() {
    private val _navigateToCardFragment = MutableLiveData<Boolean>()
    val navigateToCardFragment: LiveData<Boolean>
        get() = _navigateToCardFragment

    fun navigateToCardFragment() {
        _navigateToCardFragment.value = true
    }

    fun navigateToCardFragmentCompleted() {
        _navigateToCardFragment.value = false
    }

    private val _navigateToDispenseFragment = MutableLiveData<Boolean>()
    val navigateToDispenseFragment: LiveData<Boolean>
        get() = _navigateToDispenseFragment

    fun showDispenseFragment() {
        _navigateToDispenseFragment.value = true
    }

    fun showDispenseFragmentCompleted() {
        _navigateToDispenseFragment.value = false
    }
}