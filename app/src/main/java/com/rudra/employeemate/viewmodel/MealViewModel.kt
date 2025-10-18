package com.rudra.employeemate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rudra.employeemate.data.model.Meal
import com.rudra.employeemate.data.repository.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(private val mealRepository: MealRepository) : ViewModel() {

    val meals: StateFlow<List<Meal>> = mealRepository.mealsFlow
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val lastChecked: StateFlow<Map<String, Long>> = mealRepository.lastCheckedFlow
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyMap())

    fun addMeal(name: String, icon: String) {
        viewModelScope.launch {
            val newMeal = Meal(name, icon, false)
            mealRepository.saveMeals(meals.value + newMeal)
        }
    }

    fun deleteMeal(meal: Meal) {
        viewModelScope.launch {
            mealRepository.saveMeals(meals.value - meal)
        }
    }

    fun onMealChecked(meal: Meal, isChecked: Boolean) {
        viewModelScope.launch {
            val today = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }.timeInMillis

            val lastCheckedTimestamp = lastChecked.value[meal.name]

            if (lastCheckedTimestamp == null || lastCheckedTimestamp < today) {
                val updatedMeals = meals.value.map {
                    if (it.name == meal.name) {
                        it.copy(isChecked = isChecked)
                    } else {
                        it
                    }
                }
                mealRepository.saveMeals(updatedMeals)

                if (isChecked) {
                    val updatedLastChecked = lastChecked.value.toMutableMap()
                    updatedLastChecked[meal.name] = System.currentTimeMillis()
                    mealRepository.saveLastChecked(updatedLastChecked)
                }
            }
        }
    }
}