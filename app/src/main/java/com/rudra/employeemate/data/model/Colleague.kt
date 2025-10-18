package com.rudra.employeemate.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "colleagues")
data class Colleague(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val role: String,
    val email: String
)
