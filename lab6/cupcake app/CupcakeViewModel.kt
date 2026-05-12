package com.example.cupcakeapp.ui

import androidx.lifecycle.ViewModel
import com.example.cupcakeapp.model.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CupcakeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(OrderUiState(pickupOptions = pickupOptions()))
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

    fun setQuantity(numberCupcakes: Int) {
        _uiState.value = _uiState.value.copy(
            quantity = numberCupcakes,
            price = calculatePrice(numberCupcakes)
        )
    }

    fun setFlavor(desiredFlavor: String) {
        _uiState.value = _uiState.value.copy(flavor = desiredFlavor)
    }

    fun setDate(pickupDate: String) {
        _uiState.value = _uiState.value.copy(date = pickupDate)
    }

    fun resetOrder() {
        _uiState.value = OrderUiState(pickupOptions = pickupOptions())
    }

    private fun calculatePrice(quantity: Int): String {
        return "$${quantity * 2}"
    }

    private fun pickupOptions(): List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()

        repeat(4) {
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }

        return options
    }
}