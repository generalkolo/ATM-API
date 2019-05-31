package com.semanienterprise.atm.operations.account_selection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AccountSelectViewModelFactory(private val selection: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AccountSelectionViewModel::class.java)) {
            return AccountSelectionViewModel(selection) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}