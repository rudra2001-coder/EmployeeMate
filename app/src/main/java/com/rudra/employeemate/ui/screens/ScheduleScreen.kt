package com.rudra.employeemate.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.rudra.employeemate.data.model.Schedule
import com.rudra.employeemate.ui.components.ScheduleCard
import com.rudra.employeemate.viewmodel.ScheduleViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleScreen(viewModel: ScheduleViewModel = hiltViewModel()) {
    val schedules by viewModel.schedules.collectAsState()
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("List", "Calendar")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Schedule") },
                actions = {
                    // TODO: Add calendar icon
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                // Example of adding a new schedule
                viewModel.addSchedule(Schedule(employeeName = "Rudra", shift = "Night", date = "2025-10-20"))
            }) {
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
                0 -> ScheduleListView(schedules)
                1 -> ScheduleCalendarView()
            }
        }
    }
}

@Composable
fun ScheduleListView(schedules: List<Schedule>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(schedules) { schedule ->
            ScheduleCard(schedule)
        }
    }
}

@Composable
fun ScheduleCalendarView() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Calendar View Placeholder")
    }
}
