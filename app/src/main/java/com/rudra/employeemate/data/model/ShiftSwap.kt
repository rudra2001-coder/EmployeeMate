package com.rudra.employeemate.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shift_swap")
data class ShiftSwap(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val fromEmployee: String,
    val toEmployee: String,
    val approved: Boolean = false
)
