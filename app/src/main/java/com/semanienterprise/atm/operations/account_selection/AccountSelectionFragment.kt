package com.semanienterprise.atm.operations.account_selection


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.semanienterprise.atm.R
import com.semanienterprise.atm.databinding.FragmentAccountSelectionBinding
import kotlinx.android.synthetic.main.item_options_layout.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class AccountSelectionFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = FragmentAccountSelectionBinding.inflate(inflater)

        // receive sent argument
        val userSelection = AccountSelectionFragmentArgs.fromBundle(arguments!!).selectionType
        val selectedAtm = AccountSelectionFragmentArgs.fromBundle(arguments!!).atmCard

        //get the viewModel through viewModelFactory
        val viewModelFactory = AccountSelectViewModelFactory(userSelection, selectedAtm)
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(AccountSelectionViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        //set the text of the included buttons
        binding.currentAccount.default_btn.text = getString(R.string.current_text)
        binding.savingsAccount.default_btn.text = getString(R.string.savings_text)
        //set on click listeners for the buttons on the layout
        binding.savingsAccount.default_btn.setOnClickListener {
            viewModel.navigateToDestinationSavingsAccount()
        }

        binding.currentAccount.default_btn.setOnClickListener {
            viewModel.navigateToDestinationCurrentAccount()
        }

        //navigate to card fragment
        viewModel.navigateToCardFragment.observe(this, Observer { toNavigate ->
            if (toNavigate) {
                this.findNavController()
                    .navigate(AccountSelectionFragmentDirections.actionAccountSelectionFragmentToCardFragment())
                viewModel.navigateToCardFragmentComplete()
            }
        })

        //navigate to balance fragment
        viewModel.navigateToBalanceFragment.observe(this, Observer { toNavigate ->
            if (toNavigate) {
                this.findNavController()
                    .navigate(
                        AccountSelectionFragmentDirections.actionAccountSelectionFragmentToBalanceFragment(
                            selectedAtm
                        )
                    )
                viewModel.navigateToBalanceFragmentComplete()
            }
        })

        //navigate to withdrawal fragment
        viewModel.navigateToWithdrawalFragment.observe(this, Observer { toNavigate ->
            if (toNavigate) {
                this.findNavController()
                    .navigate(
                        AccountSelectionFragmentDirections.actionAccountSelectionFragmentToDepositAmountFragment(
                            selectedAtm
                        )
                    )
                viewModel.navigateToWithdrawalComplete()
            }
        })


        return binding.root
    }


}
