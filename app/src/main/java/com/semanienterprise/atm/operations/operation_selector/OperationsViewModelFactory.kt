package com.semanienterprise.atm.operations.operation_selector

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.semanienterprise.atm.network.AtmCard

class OperationsViewModelFactory(private val card: AtmCard) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OperationsViewModel::class.java)) {
            return OperationsViewModel(card) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}