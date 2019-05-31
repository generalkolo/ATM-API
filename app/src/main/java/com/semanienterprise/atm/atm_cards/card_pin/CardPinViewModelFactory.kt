package com.semanienterprise.atm.atm_cards.card_pin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.semanienterprise.atm.network.AtmCard

class CardPinViewModelFactory(private val card: AtmCard) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CardPinViewModel::class.java)) {
            return CardPinViewModel(card) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}