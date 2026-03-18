package com.example.claritypay.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.claritypay.R
import com.example.claritypay.data.Subscription

class ClarityViewModel : ViewModel() {
    private val _subscriptions = mutableStateListOf(
        Subscription(1, "NETFLIX", 249.0, "17-03", R.drawable.netflix_logo),
        Subscription(2, "Spotify", 129.0, "05-03", R.drawable.spotify_logo),
        Subscription(3, "Disney+", 179.0, "20-03", R.drawable.disney_logo)
    )
    val subscriptions: List<Subscription> = _subscriptions

    //resumen de gastos
    fun getTotalMonthlyExpense(): Double = _subscriptions.sumOf { it.monthlyCost }
    fun addSubscription(name: String, cost: Double, date: String) {
        val newId = (subscriptions.maxOfOrNull { it.id } ?: 0) + 1
        _subscriptions.add(Subscription(newId, name, cost, date))
    }
}
