package com.rudra.employeemate.di

import android.content.Context
import androidx.room.Room
import com.rudra.employeemate.data.db.AppDatabase
import com.rudra.employeemate.data.db.ColleagueDao
import com.rudra.employeemate.data.db.HolidayDao
import com.rudra.employeemate.data.db.MealDao
import com.rudra.employeemate.data.db.ScheduleDao
import com.rudra.employeemate.data.db.ShiftSwapDao
import com.rudra.employeemate.data.repository.EmployeeRepository
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
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "employee_mate_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideScheduleDao(appDatabase: AppDatabase): ScheduleDao = appDatabase.scheduleDao()

    @Provides
    fun provideMealDao(appDatabase: AppDatabase): MealDao = appDatabase.mealDao()

    @Provides
    fun provideColleagueDao(appDatabase: AppDatabase): ColleagueDao = appDatabase.colleagueDao()

    @Provides
    fun provideHolidayDao(appDatabase: AppDatabase): HolidayDao = appDatabase.holidayDao()

    @Provides
    fun provideShiftSwapDao(appDatabase: AppDatabase): ShiftSwapDao = appDatabase.shiftSwapDao()

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
