package com.rudra.employeemate.data.repository

import com.rudra.employeemate.data.db.ColleagueDao
import com.rudra.employeemate.data.db.HolidayDao
import com.rudra.employeemate.data.db.MealDao
import com.rudra.employeemate.data.db.ScheduleDao
import com.rudra.employeemate.data.db.ShiftSwapDao
import com.rudra.employeemate.data.model.Colleague
import com.rudra.employeemate.data.model.Holiday
import com.rudra.employeemate.data.model.Meal
import com.rudra.employeemate.data.model.Schedule
import com.rudra.employeemate.data.model.ShiftSwap
import kotlinx.coroutines.flow.Flow

class EmployeeRepository(
    private val scheduleDao: ScheduleDao,
    private val mealDao: MealDao,
    private val colleagueDao: ColleagueDao,
    private val holidayDao: HolidayDao,
    private val shiftSwapDao: ShiftSwapDao
) {

    // Schedule
    fun getAllSchedules(): Flow<List<Schedule>> = scheduleDao.getAllSchedules()
    suspend fun insertSchedule(schedule: Schedule) = scheduleDao.insertSchedule(schedule)
    suspend fun deleteSchedule(schedule: Schedule) = scheduleDao.deleteSchedule(schedule)

    // Meal
    fun getAllMeals(): Flow<List<Meal>> = mealDao.getAllMeals()
    suspend fun insertMeal(meal: Meal) = mealDao.insertMeal(meal)
    suspend fun deleteMeal(meal: Meal) = mealDao.deleteMeal(meal)

    // Colleague
    fun getAllColleagues(): Flow<List<Colleague>> = colleagueDao.getAllColleagues()
    suspend fun insertColleague(colleague: Colleague) = colleagueDao.insertColleague(colleague)
    suspend fun deleteColleague(colleague: Colleague) = colleagueDao.deleteColleague(colleague)

    // Holiday
    fun getAllHolidays(): Flow<List<Holiday>> = holidayDao.getAllHolidays()
    suspend fun insertHoliday(holiday: Holiday) = holidayDao.insert(holiday)

    // ShiftSwap
    fun getAllShiftSwaps(): Flow<List<ShiftSwap>> = shiftSwapDao.getAllShiftSwaps()
    suspend fun insertShiftSwap(shiftSwap: ShiftSwap) = shiftSwapDao.insert(shiftSwap)
}
