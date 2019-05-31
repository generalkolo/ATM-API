package com.semanienterprise.atm.operations.debit_others


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.semanienterprise.atm.databinding.FragmentOtherAmountBinding

/**
 * A simple [Fragment] subclass.
 *
 */
class OtherAmountFragment : Fragment() {

    //get the viewModel
    private val viewModel: OtherAmountViewModel by lazy {
        ViewModelProviders.of(this).get(OtherAmountViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentOtherAmountBinding.inflate(inflater)

        //get arguments
        val atmCard = OtherAmountFragmentArgs.fromBundle(arguments!!).atmCard
        binding.viewModel = viewModel

        //navigate to card fragment on click of cancel button
        viewModel.navigateToCardFragment.observe(this, Observer { toNavigate ->
            if (toNavigate) {
                this.findNavController()
                    .navigate(OtherAmountFragmentDirections.actionOtherAmountFragmentToCardFragment())
                viewModel.navigateToCardFragmentCompleted()
            }
        })

        //navigate to dispense fragment on click of enter button
        viewModel.navigateToDispenseFragment.observe(this, Observer { toNavigate ->
            if (toNavigate) {
                this.findNavController()
                    .navigate(
                        OtherAmountFragmentDirections.actionOtherAmountFragmentToReceiptFragment(
                            viewModel.userEnteredAmount!!,
                            atmCard
                        )
                    )
                viewModel.showDispenseFragmentCompleted()
            }
        })

        viewModel.getEnteredAmount.observe(this, Observer { getAmount ->
            if (getAmount) {
                viewModel.userEnteredAmount = binding.debitAmount.text.toString()
                viewModel.enteredAmountGotten()
            }
        })

        viewModel.showEmptyAmountToast.observe(this, Observer { showToast ->
            if (showToast) {
                Toast.makeText(activity, "Debit amount cannot be empty", Toast.LENGTH_SHORT).show()
                viewModel.emptyAmountToastShown()
            }
        })

        viewModel.showWrongMultiplesToast.observe(this, Observer { showToast ->
            if (showToast) {
                Toast.makeText(activity, "Debit amount should only be in multiple of 500 or 1000", Toast.LENGTH_SHORT)
                    .show()
                viewModel.wrongMultipleAmountToastShown()
            }
        })


        return binding.root
    }


}
