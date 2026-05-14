package com.example.expenseviewerapp.presentation

import com.example.expenseviewerapp.domain.model.Expense

data class ExpensesUiState(
    val isLoading: Boolean = false,
    val expenses: List<Expense> = emptyList(),
    val errorMessage: String? = null,
    val isShowingCachedData: Boolean = false
)
