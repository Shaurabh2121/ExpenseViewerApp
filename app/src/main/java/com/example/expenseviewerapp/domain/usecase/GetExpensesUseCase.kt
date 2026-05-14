package com.example.expenseviewerapp.domain.usecase

import com.example.expenseviewerapp.domain.model.Expense
import com.example.expenseviewerapp.domain.repository.ExpenseRepository
import com.example.expenseviewerapp.util.Resource
import javax.inject.Inject

class GetExpensesUseCase @Inject constructor(
    private val repository: ExpenseRepository
) {

    suspend operator fun invoke(): Resource<List<Expense>> {
        return when (val result = repository.getExpenses()) {
            is Resource.Success -> Resource.Success(
                data = result.data.sortedBy { it.date },
                isFromCache = result.isFromCache
            )
            is Resource.Error -> result
            is Resource.Loading -> result
        }
    }
}
