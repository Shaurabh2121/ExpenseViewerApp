package com.example.expenseviewerapp.domain.usecase

import com.example.expenseviewerapp.domain.model.Expense
import com.example.expenseviewerapp.domain.repository.ExpenseRepository
import com.example.expenseviewerapp.util.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.util.Date

class GetExpensesUseCaseTest {

    private lateinit var repository: ExpenseRepository
    private lateinit var useCase: GetExpensesUseCase

    @Before
    fun setUp() {
        repository = mockk()
        useCase = GetExpensesUseCase(repository)
    }

    @Test
    fun `returns expenses sorted chronologically ascending`() = runTest {
        val newer = Expense("1", "Hotel", 550.0, Date(1628000000000))
        val older = Expense("2", "Flight", 230.5, Date(1625270000000))
        coEvery { repository.getExpenses() } returns Resource.Success(listOf(newer, older))

        val result = useCase()

        assertTrue(result is Resource.Success)
        val data = (result as Resource.Success).data
        assertEquals("Flight", data[0].title)
        assertEquals("Hotel", data[1].title)
    }

    @Test
    fun `preserves isFromCache flag on success`() = runTest {
        val expense = Expense("1", "Lunch", 15.0, Date())
        coEvery { repository.getExpenses() } returns Resource.Success(listOf(expense), isFromCache = true)

        val result = useCase()

        assertTrue(result is Resource.Success)
        assertTrue((result as Resource.Success).isFromCache)
    }

    @Test
    fun `propagates error unchanged`() = runTest {
        val errorMessage = "No internet connection"
        coEvery { repository.getExpenses() } returns Resource.Error(errorMessage)

        val result = useCase()

        assertTrue(result is Resource.Error)
        assertEquals(errorMessage, (result as Resource.Error).message)
    }

    @Test
    fun `handles empty list`() = runTest {
        coEvery { repository.getExpenses() } returns Resource.Success(emptyList())

        val result = useCase()

        assertTrue(result is Resource.Success)
        assertTrue((result as Resource.Success).data.isEmpty())
        assertFalse(result.isFromCache)
    }
}
