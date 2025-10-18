package com.rudra.employeemate.data.db

import androidx.room.*
import com.rudra.employeemate.data.model.Colleague
import kotlinx.coroutines.flow.Flow

@Dao
interface ColleagueDao {
    @Query("SELECT * FROM colleagues ORDER BY name ASC")
    fun getAllColleagues(): Flow<List<Colleague>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertColleague(colleague: Colleague)

    @Delete
    suspend fun deleteColleague(colleague: Colleague)
}
