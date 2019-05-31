package com.semanienterprise.atm.atm_cards.card_pin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semanienterprise.atm.network.AtmCard

class CardPinViewModel(private val atmCard: AtmCard) : ViewModel() {
    var userPin: String? = null

    private val _displayedUserPin = MutableLiveData<String>()
    val displayedUserPin: LiveData<String>
        get() = _displayedUserPin

    private val _servicesClicked = MutableLiveData<Boolean>()
    val servicesClicked: LiveData<Boolean>
        get() = _servicesClicked

    private val _canceledClicked = MutableLiveData<Boolean>()
    val canceledClicked: LiveData<Boolean>
        get() = _canceledClicked

    private val _showWrongPinToast = MutableLiveData<Boolean>()
    val showWrongPinToast: LiveData<Boolean>
        get() = _showWrongPinToast

    val _showNoPinEnteredToast = MutableLiveData<Boolean>()
    val showNoPinEnteredToast: LiveData<Boolean>
        get() = _showNoPinEnteredToast

    private val _getUserPin = MutableLiveData<Boolean>()
    val getUserPin: LiveData<Boolean>
        get() = _getUserPin

    fun showToastCompleted() {
        _showWrongPinToast.value = false
    }

    fun pinCollectedCompleted() {
        _getUserPin.value = false
    }

    fun getUserPassword() {
        _getUserPin.value = true
    }

    fun showServicesFragment() {
        if (null == userPin || userPin?.length == 0) {
            _showNoPinEnteredToast.value = true
        } else if (userPin!! != atmCard.pin.toString()) {
            _showWrongPinToast.value = true
        } else if (userPin!! == atmCard.pin.toString()) {
            _servicesClicked.value = true
        }
    }

    fun showNoPinEnteredToastShowed() {
        _showNoPinEnteredToast.value = false
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