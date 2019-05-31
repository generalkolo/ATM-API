package com.semanienterprise.atm.operations.debit_others

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OtherAmountViewModel : ViewModel() {
    var userEnteredAmount: String? = null

    private val _navigateToCardFragment = MutableLiveData<Boolean>()
    val navigateToCardFragment: LiveData<Boolean>
        get() = _navigateToCardFragment

    val _getEnteredAmount = MutableLiveData<Boolean>()
    val getEnteredAmount: LiveData<Boolean>
        get() = _getEnteredAmount

    val _showEmptyAmountToast = MutableLiveData<Boolean>()
    val showEmptyAmountToast: LiveData<Boolean>
        get() = _showEmptyAmountToast

    fun emptyAmountToastShown() {
        _showEmptyAmountToast.value = false
    }

    val _showWrongMultiplesToast = MutableLiveData<Boolean>()
    val showWrongMultiplesToast: LiveData<Boolean>
        get() = _showWrongMultiplesToast

    fun wrongMultipleAmountToastShown() {
        _showWrongMultiplesToast.value = false
    }

    fun enteredAmountGotten() {
        _getEnteredAmount.value = false
    }

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
        if (null == userEnteredAmount || userEnteredAmount?.length == 0) {
            _showEmptyAmountToast.value = true
        } else if (userEnteredAmount!!.toInt() % 500 != 0) {
            _showWrongMultiplesToast.value = true
        } else {
            _navigateToDispenseFragment.value = true
        }
    }

    fun showDispenseFragmentCompleted() {
        _navigateToDispenseFragment.value = false
    }
}