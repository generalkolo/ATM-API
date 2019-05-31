package com.semanienterprise.atm.receipt


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.semanienterprise.atm.databinding.FragmentReceiptBinding

/**
 * A simple [Fragment] subclass.
 *
 */
class ReceiptFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //get binding value
        val binding = FragmentReceiptBinding.inflate(inflater)

        //get atm card
        val atmCard = ReceiptFragmentArgs.fromBundle(arguments!!).atmCard

        //viewmodel
        val viewModel = ViewModelProviders.of(this).get(ReceiptFragmentViewModel::class.java)

        //set layout viewmodel
        binding.viewModel = viewModel
        binding.atmCard = atmCard
        binding.lifecycleOwner = this

        viewModel.anotherTransaction.observe(this, Observer { toNavigate ->
            if (toNavigate) {
                this.findNavController().navigate(ReceiptFragmentDirections.actionReceiptFragmentToCardPin(atmCard))
                viewModel.anotherTransactionCompleted()
            }
        })
        viewModel.navigateToCardFragment.observe(this, Observer { toNavigate ->
            if (toNavigate) {
                this.findNavController().navigate(ReceiptFragmentDirections.actionReceiptFragmentToCardFragment())
                viewModel.navigateBackToCardFragmentCompleted()
            }
        })

        return binding.root
    }


}
