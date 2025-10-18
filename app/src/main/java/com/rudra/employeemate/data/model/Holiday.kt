package com.rudra.employeemate.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "holiday")
data class Holiday(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val reason: String
)
