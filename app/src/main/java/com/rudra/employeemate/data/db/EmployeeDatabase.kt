package com.rudra.employeemate.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rudra.employeemate.data.model.Colleague
import com.rudra.employeemate.data.model.Holiday
import com.rudra.employeemate.data.model.Meal
import com.rudra.employeemate.data.model.Schedule
import com.rudra.employeemate.data.model.ShiftSwap

@Database(entities = [Schedule::class, Meal::class, Colleague::class, Holiday::class, ShiftSwap::class], version = 1)
abstract class EmployeeDatabase : RoomDatabase() {
    abstract fun scheduleDao(): ScheduleDao
    abstract fun mealDao(): MealDao
    abstract fun colleagueDao(): ColleagueDao
    abstract fun holidayDao(): HolidayDao
    abstract fun shiftSwapDao(): ShiftSwapDao
}