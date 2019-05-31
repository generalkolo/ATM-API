package com.semanienterprise.atm.operations.operation_selector


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.semanienterprise.atm.R
import com.semanienterprise.atm.databinding.FragmentOperationsBinding
import kotlinx.android.synthetic.main.item_options_layout.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class OperationsFragment : Fragment() {

    companion object {
        val BALANCE_ENQUIRY = "Balance Enquiry"
        val WITHDRAWAL = "Withdrawal"
    }

    //get the viewModel
    private val viewModel: OperationsViewModel by lazy {
        ViewModelProviders.of(this).get(OperationsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = FragmentOperationsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.cashWithdrawalIncluded.default_btn.text = getString(R.string.cash_withdrawal_btn_text)
        binding.balanceEnquiryIncluded.default_btn.text = getString(R.string.balance_enquiry_btn_text)
        binding.cashWithdrawal = "Cash Withdrawal"
        binding.balanceEnquiry = "Balance Enquiry"

        //set the click listeners for the buttons included
        binding.cashWithdrawalIncluded.default_btn.setOnClickListener {
            viewModel.onNavigateToAccountSelectionToWithdrawal()
        }

        binding.balanceEnquiryIncluded.default_btn.setOnClickListener {
            viewModel.onNavigateToAccountSelectionForBalance()
        }

        //navigate to account selection for withdrawal
        viewModel.navigateToAccountSelection.observe(this, Observer {
            if (it) {
//                this.findNavController()
//                    .navigate(OperationsFragmentDirections.actionOperationsFragmentToAccountSelectionFragment(WITHDRAWAL))
                viewModel.navigateToAccountSelectionComplete()
            }
        })

        //navigate to the account select for balance enquiry
        viewModel.navigateToBalanceFragment.observe(this, Observer {
            if (it) {
//                this.findNavController()
//                    .navigate(
//                        OperationsFragmentDirections.actionOperationsFragmentToAccountSelectionFragment(
//                            BALANCE_ENQUIRY
//                        )
//                    )
                viewModel.navigateToBalanceFragmentComplete()
            }
        })

        //navigate to the card fragment
        viewModel.navigateBackToCardFragment.observe(this, Observer {
            if (it) {
                this.findNavController().navigate(OperationsFragmentDirections.actionOperationsFragmentToCardFragment())
                viewModel.navigateBackToCardFragmentComplete()
            }
        })

        return binding.root
    }
}
