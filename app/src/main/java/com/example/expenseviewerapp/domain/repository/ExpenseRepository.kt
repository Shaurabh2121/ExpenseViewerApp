package com.example.expenseviewerapp.domain.repository

import com.example.expenseviewerapp.domain.model.Expense
import com.example.expenseviewerapp.util.Resource

interface ExpenseRepository {
    suspend fun getExpenses(): Resource<List<Expense>>
}
