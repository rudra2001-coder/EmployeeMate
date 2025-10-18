package com.rudra.employeemate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rudra.employeemate.data.model.Holiday
import com.rudra.employeemate.data.repository.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HolidayViewModel @Inject constructor(
    private val repository: EmployeeRepository
) : ViewModel() {

    val holidays: StateFlow<List<Holiday>> = repository.getAllHolidays()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addHoliday(holiday: Holiday) {
        viewModelScope.launch {
            repository.insertHoliday(holiday)
        }
    }
}
