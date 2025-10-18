package com.rudra.employeemate.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.rudra.employeemate.data.model.Schedule
import com.rudra.employeemate.ui.components.Calendar
import com.rudra.employeemate.ui.components.ScheduleCard
import com.rudra.employeemate.viewmodel.ScheduleViewModel
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleScreen(viewModel: ScheduleViewModel = hiltViewModel()) {
    val schedules by viewModel.schedules.collectAsState()
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("List", "Calendar")
    var showAddScheduleDialog by remember { mutableStateOf(false) }
    var showDeleteScheduleDialog by remember { mutableStateOf<Schedule?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Schedule") },
                actions = {
                    IconButton(onClick = { selectedTab = 1 }) {
                        Icon(Icons.Rounded.DateRange, contentDescription = "Calendar View")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddScheduleDialog = true }) {
                Icon(Icons.Rounded.Add, contentDescription = "Add Schedule")
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            TabRow(selectedTabIndex = selectedTab) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(title) }
                    )
                }
            }
            when (selectedTab) {
                0 -> ScheduleListView(schedules) {
                    showDeleteScheduleDialog = it
                }
                1 -> ScheduleCalendarView()
            }
        }
    }

    if (showAddScheduleDialog) {
        AddScheduleDialog(
            onDismiss = { showAddScheduleDialog = false },
            onAddSchedule = { schedule ->
                viewModel.addSchedule(schedule)
                showAddScheduleDialog = false
            }
        )
    }

    showDeleteScheduleDialog?.let { schedule ->
        DeleteScheduleDialog(
            schedule = schedule,
            onDismiss = { showDeleteScheduleDialog = null },
            onDelete = {
                viewModel.deleteSchedule(schedule)
                showDeleteScheduleDialog = null
            }
        )
    }
}

@Composable
fun ScheduleListView(schedules: List<Schedule>, onScheduleLongClick: (Schedule) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(schedules) { schedule ->
            ScheduleCard(schedule, onLongClick = { onScheduleLongClick(schedule) })
        }
    }
}

@Composable
fun ScheduleCalendarView() {
    var selectedDate by remember { mutableStateOf<Date?>(null) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Calendar { date ->
            selectedDate = date
        }
        Text(text = selectedDate?.toString() ?: "No date selected")
    }
}

@Composable
fun AddScheduleDialog(onDismiss: () -> Unit, onAddSchedule: (Schedule) -> Unit) {
    var employeeName by remember { mutableStateOf("") }
    var shift by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Schedule") },
        text = {
            Column {
                TextField(value = employeeName, onValueChange = { employeeName = it }, label = { Text("Employee Name") })
                TextField(value = shift, onValueChange = { shift = it }, label = { Text("Shift") })
                TextField(value = date, onValueChange = { date = it }, label = { Text("Date") })
            }
        },
        confirmButton = {
            Button(onClick = { onAddSchedule(Schedule(employeeName = employeeName, shift = shift, date = date)) }) {
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
fun DeleteScheduleDialog(schedule: Schedule, onDismiss: () -> Unit, onDelete: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Delete Schedule") },
        text = { Text("Are you sure you want to delete this schedule?") },
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
