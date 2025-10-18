package com.rudra.employeemate.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rudra.employeemate.data.model.Colleague
import com.rudra.employeemate.ui.components.AddColleagueDialog
import com.rudra.employeemate.ui.components.ColleagueCard
import com.rudra.employeemate.ui.components.SearchBar
import com.rudra.employeemate.viewmodel.ColleagueViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColleaguesScreen(viewModel: ColleagueViewModel = hiltViewModel()) {
    val colleagues by viewModel.colleagues.collectAsState()
    var query by remember { mutableStateOf("") }
    var showAddDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf<Colleague?>(null) }

    if (showAddDialog) {
        AddColleagueDialog(
            onDismiss = { showAddDialog = false },
            onConfirm = {
                viewModel.addColleague(it)
                showAddDialog = false
            }
        )
    }

    showDeleteDialog?.let { colleague ->
        AlertDialog(
            onDismissRequest = { showDeleteDialog = null },
            title = { Text("Delete Colleague") },
            text = { Text("Are you sure you want to delete ${colleague.name}?") },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.deleteColleague(colleague)
                        showDeleteDialog = null
                    }
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = null }) {
                    Text("Cancel")
                }
            }
        )
    }

    val filteredColleagues = colleagues.filter {
        it.name.contains(query, ignoreCase = true) || it.role.contains(query, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Colleagues") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Rounded.Add, contentDescription = "Add Colleague")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SearchBar(
                query = query,
                onQueryChange = { query = it },
                onSearch = {},
                active = false,
                onActiveChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) { }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(filteredColleagues) { colleague ->
                    ColleagueCard(colleague, onLongClick = { showDeleteDialog = colleague })
                }
            }
        }
    }
}
