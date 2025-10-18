package com.rudra.employeemate.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rudra.employeemate.data.model.ShiftSwap
import kotlinx.coroutines.flow.Flow

@Dao
interface ShiftSwapDao {
    @Query("SELECT * FROM shift_swap ORDER BY date DESC")
    fun getAllShiftSwaps(): Flow<List<ShiftSwap>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(shiftSwap: ShiftSwap)
}
