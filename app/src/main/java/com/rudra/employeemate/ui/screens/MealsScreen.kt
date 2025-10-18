package com.rudra.employeemate.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealsScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Meal Tracker") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* TODO: Export CSV */ }) {
                // TODO: Replace with export icon
                Icon(Icons.Rounded.Check, contentDescription = "Export CSV")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TodayMealsCard()
            MonthlySummary()
        }
    }
}

@Composable
fun TodayMealsCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Today's Meals", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                MealItem(name = "Breakfast", icon = "🍳")
                MealItem(name = "Lunch", icon = "🍱")
                MealItem(name = "Dinner", icon = "🍲")
                MealItem(name = "Snacks", icon = "🍪")
            }
        }
    }
}

@Composable
fun MealItem(name: String, icon: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(icon, style = MaterialTheme.typography.headlineLarge)
        Checkbox(checked = false, onCheckedChange = {})
        Text(name, style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
fun MonthlySummary() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Monthly Summary", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Box(contentAlignment = Alignment.Center, modifier = Modifier.size(150.dp)) {
                CircularProgressIndicator(progress = { 0.25f }, modifier = Modifier.fillMaxSize())
                Text("23 / 90")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text("Resets at the start of the month", style = MaterialTheme.typography.bodySmall)
        }
    }
}
