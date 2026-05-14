package com.example.expenseviewerapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.expenseviewerapp.data.local.entity.ExpenseEntity

@Database(entities = [ExpenseEntity::class], version = 1, exportSchema = false)
abstract class ExpenseDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
}
