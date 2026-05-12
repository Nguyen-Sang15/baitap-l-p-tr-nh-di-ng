package com.example.cupcakeapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SelectOptionScreen(
    options: List<String>,
    onSelectionChanged: (String) -> Unit,
    onNextClicked: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {

        options.forEach {
            RowOption(option = it, onClick = onSelectionChanged)
        }

        Button(
            onClick = onNextClicked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text("Next")
        }
    }
}

@Composable
fun RowOption(
    option: String,
    onClick: (String) -> Unit
) {
    androidx.compose.foundation.layout.Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        RadioButton(
            selected = false,
            onClick = { onClick(option) }
        )

        Text(
            text = option,
            modifier = Modifier.padding(top = 12.dp)
        )
    }
}