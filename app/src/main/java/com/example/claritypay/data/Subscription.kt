package com.example.claritypay.data

import androidx.annotation.DrawableRes

data class Subscription(
    val id: Int,
    val name: String,
    val monthlyCost: Double,
    val payDate: String,
    @DrawableRes val logo: Int = android.R.drawable.ic_menu_agenda
)