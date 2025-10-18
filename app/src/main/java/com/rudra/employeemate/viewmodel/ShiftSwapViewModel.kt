package com.rudra.employeemate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rudra.employeemate.data.model.ShiftSwap
import com.rudra.employeemate.data.repository.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShiftSwapViewModel @Inject constructor(
    private val repository: EmployeeRepository
) : ViewModel() {

    val shiftSwaps: StateFlow<List<ShiftSwap>> = repository.getAllShiftSwaps()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addShiftSwap(shiftSwap: ShiftSwap) {
        viewModelScope.launch {
            repository.insertShiftSwap(shiftSwap)
        }
    }
}
