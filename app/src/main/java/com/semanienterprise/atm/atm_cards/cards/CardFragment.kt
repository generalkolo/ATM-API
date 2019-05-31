package com.semanienterprise.atm.atm_cards.cards


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.semanienterprise.atm.databinding.FragmentCardBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 */
class CardFragment : Fragment() {

    //get the viewModel
    private val viewModel: CardFragmentViewModel by lazy {
        ViewModelProviders.of(this).get(CardFragmentViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //get binding variable
        val binding: FragmentCardBinding = FragmentCardBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.bankCards.adapter = CardAdapter(onClickListener { atmCard ->
            viewModel.atmCardReceived(atmCard)
        })

        viewModel.atmCardReceived.observe(this, Observer { atmCard ->
            atmCard?.let {
                this.findNavController().navigate(CardFragmentDirections.actionCardFragmentToCardPin(atmCard))
                viewModel.onNavigateToPassScreenComplete()
            }
        })
        return binding.root
    }
}
