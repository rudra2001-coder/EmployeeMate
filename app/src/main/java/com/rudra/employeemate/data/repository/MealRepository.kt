package com.rudra.employeemate.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rudra.employeemate.data.model.Meal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "meals")

class MealRepository(private val context: Context) {

    private val gson = Gson()
    private val mealsKey = stringPreferencesKey("meals")
    private val lastCheckedKey = stringPreferencesKey("last_checked")

    val mealsFlow: Flow<List<Meal>> = context.dataStore.data
        .map {
            val json = it[mealsKey]
            if (json.isNullOrEmpty()) {
                emptyList()
            } else {
                val type = object : TypeToken<List<Meal>>() {}.type
                gson.fromJson(json, type)
            }
        }

    suspend fun saveMeals(meals: List<Meal>) {
        context.dataStore.edit {
            it[mealsKey] = gson.toJson(meals)
        }
    }

    val lastCheckedFlow: Flow<Map<String, Long>> = context.dataStore.data
        .map {
            val json = it[lastCheckedKey]
            if (json.isNullOrEmpty()) {
                emptyMap()
            } else {
                val type = object : TypeToken<Map<String, Long>>() {}.type
                gson.fromJson(json, type)
            }
        }

    suspend fun saveLastChecked(lastChecked: Map<String, Long>) {
        context.dataStore.edit {
            it[lastCheckedKey] = gson.toJson(lastChecked)
        }
    }
}