package com.rudra.employeemate.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rudra.employeemate.data.model.*

@Database(
    entities = [
        Schedule::class,
        Meal::class,
        Holiday::class,
        ShiftSwap::class,
        Colleague::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun scheduleDao(): ScheduleDao
    abstract fun mealDao(): MealDao
    abstract fun holidayDao(): HolidayDao
    abstract fun shiftSwapDao(): ShiftSwapDao
    abstract fun colleagueDao(): ColleagueDao
}
