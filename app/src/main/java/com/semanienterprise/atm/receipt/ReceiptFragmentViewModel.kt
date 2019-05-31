package com.semanienterprise.atm.receipt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReceiptFragmentViewModel : ViewModel() {
    private val _navigateToCardFragment = MutableLiveData<Boolean>()
    val navigateToCardFragment: LiveData<Boolean>
        get() = _navigateToCardFragment

    val _anotherTransaction = MutableLiveData<Boolean>()
    val anotherTransaction: LiveData<Boolean>
        get() = _anotherTransaction

    fun navigateBackToCardFragment() {
        _navigateToCardFragment.value = true
    }

    fun navigateBackToCardFragmentCompleted() {
        _navigateToCardFragment.value = false
    }

    fun anotherTransaction() {
        _anotherTransaction.value = true
    }

    fun anotherTransactionCompleted() {
        _anotherTransaction.value = false
    }

}