package com.rudra.employeemate.di

import android.content.Context
import com.rudra.employeemate.data.db.ColleagueDao
import com.rudra.employeemate.data.db.HolidayDao
import com.rudra.employeemate.data.db.MealDao
import com.rudra.employeemate.data.db.ScheduleDao
import com.rudra.employeemate.data.db.ShiftSwapDao
import com.rudra.employeemate.data.repository.EmployeeRepository
import com.rudra.employeemate.data.repository.MealRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMealRepository(@ApplicationContext context: Context): MealRepository {
        return MealRepository(context)
    }

    @Provides
    @Singleton
    fun provideEmployeeRepository(
        scheduleDao: ScheduleDao,
        mealDao: MealDao,
        colleagueDao: ColleagueDao,
        holidayDao: HolidayDao,
        shiftSwapDao: ShiftSwapDao
    ): EmployeeRepository {
        return EmployeeRepository(scheduleDao, mealDao, colleagueDao, holidayDao, shiftSwapDao)
    }
}
