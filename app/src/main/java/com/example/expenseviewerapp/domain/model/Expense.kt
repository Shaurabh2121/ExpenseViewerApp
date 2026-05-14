package com.example.expenseviewerapp.domain.model

import java.util.Date

data class Expense(
    val id: String,
    val title: String,
    val amount: Double,
    val date: Date
)
