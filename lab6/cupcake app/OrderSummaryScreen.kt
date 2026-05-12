package com.example.cupcakeapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OrderSummaryScreen(
    quantity: Int,
    flavor: String,
    date: String,
    price: String,
    onSendClicked: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {

        Text(text = "Quantity: $quantity")
        Text(text = "Flavor: $flavor")
        Text(text = "Pickup Date: $date")
        Text(text = "Total Price: $price")

        Button(
            onClick = onSendClicked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text("Send Order")
        }
    }
}