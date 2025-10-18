package com.rudra.employeemate.ui.screens

import android.widget.Toast
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.rudra.employeemate.data.model.Meal
import com.rudra.employeemate.ui.components.MealCard
import com.rudra.employeemate.viewmodel.MealViewModel
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealsScreen(viewModel: MealViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val meals by viewModel.meals.collectAsState()
    val lastChecked by viewModel.lastChecked.collectAsState()

    var showAddMealDialog by remember { mutableStateOf(false) }
    var showDeleteMealDialog by remember { mutableStateOf<Meal?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Meal Tracker") })
        },
        floatingActionButton = {
            Row {
                FloatingActionButton(onClick = { Toast.makeText(context, "Export not implemented yet", Toast.LENGTH_SHORT).show() }) {
                    Icon(Icons.Rounded.Share, contentDescription = "Export CSV")
                }
                Spacer(modifier = Modifier.size(16.dp))
                FloatingActionButton(onClick = { showAddMealDialog = true }) {
                    Icon(Icons.Rounded.Add, contentDescription = "Add Meal")
                }
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
            TodayMealsCard(meals, onMealChecked = { meal, isChecked ->
                val today = Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, 0)
                    set(Calendar.MINUTE, 0)
                    set(Calendar.SECOND, 0)
                    set(Calendar.MILLISECOND, 0)
                }.timeInMillis

                val lastCheckedTimestamp = lastChecked[meal.name]

                if (lastCheckedTimestamp != null && lastCheckedTimestamp >= today) {
                    Toast.makeText(context, "You can only check once a day", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.onMealChecked(meal, isChecked)
                }
            }, onMealLongClick = { meal ->
                showDeleteMealDialog = meal
            })
            MonthlySummary(meals)
        }
    }

    if (showAddMealDialog) {
        AddMealDialog(
            onDismiss = { showAddMealDialog = false },
            onAddMeal = { name, icon ->
                viewModel.addMeal(name, icon)
                showAddMealDialog = false
            }
        )
    }

    showDeleteMealDialog?.let { meal ->
        DeleteMealDialog(
            meal = meal,
            onDismiss = { showDeleteMealDialog = null },
            onDelete = {
                viewModel.deleteMeal(meal)
                showDeleteMealDialog = null
            }
        )
    }
}

@Composable
fun TodayMealsCard(meals: List<Meal>, onMealChecked: (Meal, Boolean) -> Unit, onMealLongClick: (Meal) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Today's Meals", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(meals) { meal ->
                    MealCard(
                        meal = meal,
                        onCheckedChange = { isChecked -> onMealChecked(meal, isChecked) },
                        onLongClick = { onMealLongClick(meal) }
                    )
                }
            }
        }
    }
}


@Composable
fun MonthlySummary(meals: List<Meal>) {
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
            Box(contentAlignment = Alignment.Center, modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)) {
                AndroidView(factory = { context ->
                    BarChart(context).apply {
                        val entries = meals.mapIndexed { index, meal ->
                            BarEntry(index.toFloat(), if (meal.isChecked) 1f else 0f)
                        }
                        val dataSet = BarDataSet(entries, "Meals")
                        this.data = BarData(dataSet)
                        invalidate()
                    }
                })
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text("Resets at the start of the month", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun AddMealDialog(onDismiss: () -> Unit, onAddMeal: (String, String) -> Unit) {
    var name by remember { mutableStateOf("") }
    var icon by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Meal") },
        text = {
            Column {
                TextField(value = name, onValueChange = { name = it }, label = { Text("Meal Name") })
                Spacer(modifier = Modifier.height(8.dp))
                TextField(value = icon, onValueChange = { icon = it }, label = { Text("Emoji Icon") })
            }
        },
        confirmButton = {
            Button(onClick = { onAddMeal(name, icon) }) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun DeleteMealDialog(meal: Meal, onDismiss: () -> Unit, onDelete: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Delete Meal") },
        text = { Text("Are you sure you want to delete ${meal.name}?") },
        confirmButton = {
            Button(onClick = onDelete) {
                Text("Delete")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
