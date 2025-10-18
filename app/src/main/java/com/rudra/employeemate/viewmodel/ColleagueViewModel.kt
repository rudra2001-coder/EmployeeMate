package com.rudra.employeemate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rudra.employeemate.data.model.Colleague
import com.rudra.employeemate.data.repository.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ColleagueViewModel @Inject constructor(
    private val repository: EmployeeRepository
) : ViewModel() {

    val colleagues: StateFlow<List<Colleague>> = repository.getAllColleagues()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addColleague(colleague: Colleague) {
        viewModelScope.launch {
            repository.insertColleague(colleague)
        }
    }

    fun deleteColleague(colleague: Colleague) {
        viewModelScope.launch {
            repository.deleteColleague(colleague)
        }
    }
}
