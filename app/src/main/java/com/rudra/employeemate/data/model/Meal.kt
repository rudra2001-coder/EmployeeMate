package com.rudra.employeemate.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meals")
data class Meal(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val employeeName: String,
    val mealType: String,
    val date: String
)
