package com.semanienterprise.atm.operations.debit_amount


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.semanienterprise.atm.R
import com.semanienterprise.atm.databinding.FragmentDepositAmountBinding
import kotlinx.android.synthetic.main.item_options_layout.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 */
class WithdrawalAmountFragment : Fragment() {

    //get the viewModel
    private val viewModel: WithdrawalAmountViewModel by lazy {
        ViewModelProviders.of(this).get(WithdrawalAmountViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentDepositAmountBinding.inflate(inflater)

        //get sent atm card
        val atmCard = WithdrawalAmountFragmentArgs.fromBundle(arguments!!).atmCard

        //set the text of the buttons
        binding.fiveHundredNotes.default_btn.text = getString(R.string.five_hundred_text)
        binding.oneThousandNotes.default_btn.text = getString(R.string.one_thousand_text)
        binding.twoThousandNotes.default_btn.text = getString(R.string.two_thousand_text)
        binding.threeThousandNotes.default_btn.text = getString(R.string.three_thousand_text)
        binding.fiveThousandNotes.default_btn.text = getString(R.string.five_thousand_text)
        binding.amountButton.default_btn.text = getString(R.string.others_text)

        //set the onclick listeners for the buttons
        binding.amountButton.default_btn.setOnClickListener {
            viewModel.navigateToOthersFragment()
        }
        binding.fiveHundredNotes.default_btn.setOnClickListener {
            viewModel.navigateToDispenseFragment("500")
        }
        binding.oneThousandNotes.default_btn.setOnClickListener {
            viewModel.navigateToDispenseFragment("1000")
        }
        binding.twoThousandNotes.default_btn.setOnClickListener {
            viewModel.navigateToDispenseFragment("2000")
        }
        binding.threeThousandNotes.default_btn.setOnClickListener {
            viewModel.navigateToDispenseFragment("3000")
        }
        binding.fiveThousandNotes.default_btn.setOnClickListener {
            viewModel.navigateToDispenseFragment("5000")
        }

        viewModel.navigateToOthers.observe(this, Observer { toNavigate ->
            if (toNavigate) {
                this.findNavController()
                    .navigate(
                        WithdrawalAmountFragmentDirections.actionDepositAmountFragmentToOtherAmountFragment(
                            atmCard
                        )
                    )
                viewModel.navigateToOthersFragmentCompleted()
            }
        })

        viewModel.navigateToCardFragment.observe(this, Observer { toNavigate ->
            if (toNavigate) {
                this.findNavController()
                    .navigate(WithdrawalAmountFragmentDirections.actionDepositAmountFragmentToCardFragment())
                viewModel.navigateToCardFragmentComplete()
            }
        })

        viewModel.navigateToDispenseFragment.observe(this, Observer { debitAmount ->
            debitAmount?.let {
                this.findNavController()
                    .navigate(
                        WithdrawalAmountFragmentDirections.actionDepositAmountFragmentToReceiptFragment(
                            it,
                            atmCard
                        )
                    )
                viewModel.navigateToDispenseFragmentCompleted()
            }
        })

        return binding.root
    }


}
