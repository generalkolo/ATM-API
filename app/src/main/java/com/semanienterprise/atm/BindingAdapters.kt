package com.semanienterprise.atm

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.semanienterprise.atm.atm_cards.cards.CardAdapter
import com.semanienterprise.atm.network.AtmCard

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<AtmCard>?) {
    val adapter = recyclerView.adapter as CardAdapter
    adapter.submitList(data)
}