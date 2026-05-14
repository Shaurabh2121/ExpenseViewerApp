package com.example.expenseviewerapp.data.repository

import com.example.expenseviewerapp.data.local.ExpenseDao
import com.example.expenseviewerapp.data.mapper.ExpenseMapper
import com.example.expenseviewerapp.data.remote.ExpenseApiService
import com.example.expenseviewerapp.domain.model.Expense
import com.example.expenseviewerapp.domain.repository.ExpenseRepository
import com.example.expenseviewerapp.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class ExpenseRepositoryImpl @Inject constructor(
    private val apiService: ExpenseApiService,
    private val dao: ExpenseDao
) : ExpenseRepository {

    override suspend fun getExpenses(): Resource<List<Expense>> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getExpenses().execute()
            if (response.isSuccessful && response.body() != null) {
                val entities = ExpenseMapper.dtosToEntities(response.body()!!)
                dao.replaceAll(entities)
                Resource.Success(ExpenseMapper.entitiesToDomain(dao.getAll()))
            } else {
                fallbackToCache("Server error: ${response.code()}")
            }
        } catch (e: IOException) {
            fallbackToCache("No internet connection", e)
        } catch (e: Exception) {
            fallbackToCache("Unexpected error: ${e.message}", e)
        }
    }

    private suspend fun fallbackToCache(
        message: String,
        cause: Throwable? = null
    ): Resource<List<Expense>> {
        val cached = dao.getAll()
        return if (cached.isNotEmpty()) {
            Resource.Success(ExpenseMapper.entitiesToDomain(cached), isFromCache = true)
        } else {
            Resource.Error(message, cause)
        }
    }
}
