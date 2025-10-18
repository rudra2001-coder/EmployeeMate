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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rudra.employeemate.data.model.Colleague
import com.rudra.employeemate.ui.components.ColleagueCard
import com.rudra.employeemate.viewmodel.ColleagueViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColleaguesScreen(viewModel: ColleagueViewModel = hiltViewModel()) {
    val colleagues by viewModel.colleagues.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Colleagues") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.addColleague(Colleague(name = "Tanvir", role = "QA Engineer", email = "tanvir@example.com"))
            }) {
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
                query = "",
                onQueryChange = {},
                onSearch = {},
                active = false,
                onActiveChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) { }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(colleagues) {
                    ColleagueCard(it)
                }
            }
        }
    }
}
