package com.rudra.employeemate.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun AddMealDialog(onDismiss: () -> Unit, onConfirm: (String) -> Unit) {
    var meal by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Card {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Add a meal")
                Spacer(modifier = Modifier.height(8.dp))
                TextField(value = meal, onValueChange = { meal = it })
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { onConfirm(meal) }) {
                    Text(text = "Add")
                }
            }
        }
    }
}
