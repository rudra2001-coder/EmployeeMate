package com.rudra.employeemate.di

import android.content.Context
import androidx.room.Room
import com.rudra.employeemate.data.db.ColleagueDao
import com.rudra.employeemate.data.db.EmployeeDatabase
import com.rudra.employeemate.data.db.HolidayDao
import com.rudra.employeemate.data.db.MealDao
import com.rudra.employeemate.data.db.ScheduleDao
import com.rudra.employeemate.data.db.ShiftSwapDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideEmployeeDatabase(@ApplicationContext context: Context): EmployeeDatabase {
        return Room.databaseBuilder(
            context,
            EmployeeDatabase::class.java,
            "employee_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideScheduleDao(database: EmployeeDatabase): ScheduleDao {
        return database.scheduleDao()
    }

    @Provides
    @Singleton
    fun provideMealDao(database: EmployeeDatabase): MealDao {
        return database.mealDao()
    }

    @Provides
    @Singleton
    fun provideColleagueDao(database: EmployeeDatabase): ColleagueDao {
        return database.colleagueDao()
    }

    @Provides
    @Singleton
    fun provideHolidayDao(database: EmployeeDatabase): HolidayDao {
        return database.holidayDao()
    }

    @Provides
    @Singleton
    fun provideShiftSwapDao(database: EmployeeDatabase): ShiftSwapDao {
        return database.shiftSwapDao()
    }
}
