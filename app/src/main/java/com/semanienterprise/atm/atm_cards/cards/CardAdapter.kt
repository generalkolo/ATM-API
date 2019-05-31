package com.semanienterprise.atm.atm_cards.cards


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.semanienterprise.atm.databinding.ItemCardLayoutBinding
import com.semanienterprise.atm.network.AtmCard

class CardAdapter(private val clickListener: onClickListener) : ListAdapter<AtmCard, CardAdapter.CardViewHolder>(DiffUtilsCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(ItemCardLayoutBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val atmCard = getItem(position)
        holder.bind(atmCard)
        holder.itemView.setOnClickListener {
            clickListener.onClick(atmCard)
        }
    }

    class CardViewHolder(private val binding: ItemCardLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(atmCard: AtmCard) {
            binding.atmCard = atmCard
            binding.executePendingBindings()
        }
    }

    object DiffUtilsCallBack : DiffUtil.ItemCallback<AtmCard>() {
        override fun areItemsTheSame(oldItem: AtmCard, newItem: AtmCard): Boolean {
            return newItem === oldItem
        }

        override fun areContentsTheSame(oldItem: AtmCard, newItem: AtmCard): Boolean {
            return oldItem.cardNumber == newItem.cardNumber
        }
    }
}

class onClickListener(val onClickListener: (atmCard: AtmCard) -> Unit) {
    fun onClick(atmCard: AtmCard) = onClickListener(atmCard)
}

