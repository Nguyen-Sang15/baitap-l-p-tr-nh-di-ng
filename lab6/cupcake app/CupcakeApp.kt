package com.example.cupcakeapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.cupcakeapp.data.DataSource

@Composable
fun CupcakeApp(viewModel: CupcakeViewModel) {

    val uiState by viewModel.uiState.collectAsState()

    StartOrderScreen(
        onNextButtonClicked = {
            viewModel.setQuantity(it)
        }
    )

    SelectOptionScreen(
        options = DataSource.flavors,
        onSelectionChanged = {
            viewModel.setFlavor(it)
        },
        onNextClicked = {}
    )

    OrderSummaryScreen(
        quantity = uiState.quantity,
        flavor = uiState.flavor,
        date = uiState.date,
        price = uiState.price,
        onSendClicked = {}
    )
}