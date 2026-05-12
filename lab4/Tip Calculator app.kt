package com.example.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.tipcalculator.ui.theme.TipCalculatorTheme
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            TipCalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TipCalculatorApp()
                }
            }
        }
    }
}

@Composable
fun TipCalculatorApp() {

    var amountInput by remember {
        mutableStateOf("")
    }

    var tipInput by remember {
        mutableStateOf("")
    }

    var roundUp by remember {
        mutableStateOf(false)
    }

    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val tipPercent = tipInput.toDoubleOrNull() ?: 0.0

    val tip = calculateTip(
        amount = amount,
        tipPercent = tipPercent,
        roundUp = roundUp
    )

    val total = amount + (
            tip.replace("[^\\d.]".toRegex(), "").toDoubleOrNull() ?: 0.0
            )

    Column(
        modifier = Modifier
            .padding(32.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Text(
            text = "Tip Calculator",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = amountInput,
            onValueChange = { amountInput = it },
            label = { Text("Bill Amount") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = tipInput,
            onValueChange = { tipInput = it },
            label = { Text("Tip Percentage") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Switch(
                checked = roundUp,
                onCheckedChange = { roundUp = it }
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(text = "Round Up Tip")
        }

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Text(
                    text = "Tip Amount: $tip",
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = "Total Amount: ${
                        NumberFormat.getCurrencyInstance().format(total)
                    }",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

private fun calculateTip(
    amount: Double,
    tipPercent: Double,
    roundUp: Boolean
): String {

    var tip = tipPercent / 100 * amount

    if (roundUp) {
        tip = ceil(tip)
    }

    return NumberFormat.getCurrencyInstance().format(tip)
}