package com.semanienterprise.atm.operations.account_selection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.semanienterprise.atm.network.AtmCard

class AccountSelectViewModelFactory(private val selection: String, private val atmCard: AtmCard) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AccountSelectionViewModel::class.java)) {
            return AccountSelectionViewModel(selection, atmCard) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}