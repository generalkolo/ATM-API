package com.semanienterprise.atm.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AtmCard(
    val type: String,
    @Json(name = "card_number") val cardNumber: String,
    @Json(name = "expiry_date") val expiryDate: String,
    @Json(name = "card_name") val cardName: String,
    val pin: Int,
    @Json(name = "account_balance") val accountBalance: Int
) : Parcelable