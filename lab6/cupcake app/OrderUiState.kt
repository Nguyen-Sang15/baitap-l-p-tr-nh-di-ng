package com.example.cupcakeapp.model

data class OrderUiState(
    val quantity: Int = 0,
    val flavor: String = "",
    val date: String = "",
    val price: String = "$0",
    val pickupOptions: List<String> = listOf()
)