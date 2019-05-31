package com.semanienterprise.atm.atm_cards.card_pin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semanienterprise.atm.network.AtmCard

class CardPinViewModel(private val atmCard: AtmCard) : ViewModel() {
    var userPin: String? = null

    private val _servicesClicked = MutableLiveData<Boolean>()
    val servicesClicked: LiveData<Boolean>
        get() = _servicesClicked

    private val _canceledClicked = MutableLiveData<Boolean>()
    val canceledClicked: LiveData<Boolean>
        get() = _canceledClicked

    private val _showToast = MutableLiveData<Boolean>()
    val showToast: LiveData<Boolean>
        get() = _showToast

    private val _getUserPin = MutableLiveData<Boolean>()
    val getUserPin: LiveData<Boolean>
        get() = _getUserPin

    fun showToastCompleted() {
        _showToast.value = false
    }

    fun pinCollectedCompleted() {
        _getUserPin.value = false
    }

    fun showServicesFragment() {
        _getUserPin.value = true
        if (userPin!! == atmCard.pin.toString()) {
            _servicesClicked.value = true
        } else
            _showToast.value = true
    }

    fun onServicesClickedFinished() {
        _servicesClicked.value = false
    }

    fun navigateBackToPinFragment() {
        _canceledClicked.value = true
    }

    fun onCanceledClicked() {
        _canceledClicked.value = false
    }
}