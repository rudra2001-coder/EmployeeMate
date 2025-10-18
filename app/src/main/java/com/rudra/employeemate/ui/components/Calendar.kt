package com.rudra.employeemate.ui.components

import androidx.compose.runtime.Composable
import java.util.Calendar
import java.util.Date

@Composable
fun Calendar(onDateSelected: (Date) -> Unit) {
    // For now, this is a placeholder. A real calendar implementation would be more complex.
    // You might want to use a library for this.
    val calendar = Calendar.getInstance()
    val today = calendar.time
    onDateSelected(today)
}
