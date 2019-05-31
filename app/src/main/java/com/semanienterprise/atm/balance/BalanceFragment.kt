package com.semanienterprise.atm.balance


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.semanienterprise.atm.databinding.FragmentBalanceBinding

/**
 * A simple [Fragment] subclass.
 */
class BalanceFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //get binding element
        val binding = FragmentBalanceBinding.inflate(inflater)

        //get viewModel
        val viewModel = ViewModelProviders.of(this).get(BalanceFragmentViewModel::class.java)
        binding.viewModel = viewModel

        //get atmCard
        val atmCard = BalanceFragmentArgs.fromBundle(arguments!!).atmCard
        binding.atmCard = atmCard

        viewModel.navigateBackToCardFragment.observe(this, Observer { isNavigate ->
            if (isNavigate) {
                this.findNavController().navigate(BalanceFragmentDirections.actionBalanceFragmentToCardFragment())
                viewModel.navigateBackToCardFragmentCompleted()
            }
        })

        viewModel.navigateBackToPinFragment.observe(this, Observer { isNavigate ->
            if (isNavigate) {
                this.findNavController().navigate(BalanceFragmentDirections.actionBalanceFragmentToCardPinReal(atmCard))
                viewModel.navigateToPinFragmentCompleted()
            }
        })

        return binding.root
    }
}
