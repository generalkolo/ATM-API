package com.semanienterprise.atm.atm_cards.card_pin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.semanienterprise.atm.databinding.FragmentCardPinBinding

/**
 * A simple [Fragment] subclass.
 *
 */
class CardPin : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentCardPinBinding = FragmentCardPinBinding.inflate(inflater)

        val atmCardReceived = CardPinArgs.fromBundle(arguments!!).atmCard

        //get viewModel
        val viewModelFactory = CardPinViewModelFactory(atmCardReceived)
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(CardPinViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.servicesClicked.observe(this, Observer { isClicked ->
            if (isClicked) {
                this.findNavController().navigate(CardPinDirections.actionCardPinToOperationsFragment())
                viewModel.onServicesClickedFinished()
            }
        })

        //get user pin
        viewModel.getUserPin.observe(this, Observer { getPassword ->
            if (getPassword) {
                viewModel.userPin = binding.cardPinEt.text.toString()
                viewModel.pinCollectedCompleted()
            }
        })

        viewModel.canceledClicked.observe(this, Observer { isClicked ->
            if (isClicked) {
                //TODO: Fix the cancel logic to pop the backStack
                this.findNavController().navigate(CardPinDirections.actionCardPinToCardFragment())
                viewModel.onCanceledClicked()
            }
        })

        viewModel.showToast.observe(this, Observer { showToast ->
            Toast.makeText(activity, "Wrong Pin Entered", Toast.LENGTH_SHORT).show()
            viewModel.showToastCompleted()
        })
        return binding.root
    }
}
