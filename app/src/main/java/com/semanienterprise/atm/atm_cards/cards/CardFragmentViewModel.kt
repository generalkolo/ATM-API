package com.semanienterprise.atm.atm_cards.cards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semanienterprise.atm.network.AtmApi
import com.semanienterprise.atm.network.AtmCard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CardFragmentViewModel : ViewModel() {
    //coroutines
    val viewModelJob = Job()
    val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        getBankCardsFromApi()
    }

    private val _bankCards = MutableLiveData<List<AtmCard>>()
    val bankCards: LiveData<List<AtmCard>>
        get() = _bankCards

    private val _atmCardReceived = MutableLiveData<AtmCard>()
    val atmCardReceived: LiveData<AtmCard>
        get() = _atmCardReceived

    fun atmCardReceived(atmCard: AtmCard) {
        _atmCardReceived.value = atmCard
    }

    fun onNavigateToPassScreenComplete() {
        _atmCardReceived.value = null
    }

    fun getBankCardsFromApi() {
        uiScope.launch {
            val getCardsDeferred = AtmApi.retrofitService.getBankCards()
            try {
                val listResult = getCardsDeferred.await()
                if (listResult.isNotEmpty())
                    _bankCards.value = listResult
            } catch (e: Exception) {
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}