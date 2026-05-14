package com.example.expenseviewerapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.expenseviewerapp.data.local.entity.ExpenseEntity

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM expenses ORDER BY dateMillis ASC")
    suspend fun getAll(): List<ExpenseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<ExpenseEntity>)

    @Query("DELETE FROM expenses")
    suspend fun clear()

    @Transaction
    suspend fun replaceAll(items: List<ExpenseEntity>) {
        clear()
        insertAll(items)
    }
}
