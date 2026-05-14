package com.example.expenseviewerapp.data.repository

import com.example.expenseviewerapp.data.local.ExpenseDao
import com.example.expenseviewerapp.data.local.entity.ExpenseEntity
import com.example.expenseviewerapp.data.remote.ExpenseApiService
import com.example.expenseviewerapp.data.remote.dto.ExpenseDto
import com.example.expenseviewerapp.util.Resource
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class ExpenseRepositoryImplTest {

    private lateinit var apiService: ExpenseApiService
    private lateinit var dao: ExpenseDao
    private lateinit var repository: ExpenseRepositoryImpl
    private val testDispatcher = StandardTestDispatcher()

    private val sampleDtos = listOf(
        ExpenseDto("1", "Flight to SF", 230.50, "2021-07-03T01:50:00+01:00"),
        ExpenseDto("2", "Hotel", 550.00, "2021-08-03T01:50:00+01:00")
    )

    private val sampleEntities = listOf(
        ExpenseEntity("1", "Flight to SF", 230.50, 1625273400000),
        ExpenseEntity("2", "Hotel", 550.00, 1627951800000)
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        apiService = mockk()
        dao = mockk()
        repository = ExpenseRepositoryImpl(apiService, dao)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `network success caches and returns fresh data`() = runTest {
        val call = mockk<Call<List<ExpenseDto>>>()
        every { apiService.getExpenses() } returns call
        every { call.execute() } returns Response.success(sampleDtos)
        coEvery { dao.replaceAll(any()) } just Runs
        coEvery { dao.getAll() } returns sampleEntities

        val result = repository.getExpenses()

        assertTrue(result is Resource.Success)
        val success = result as Resource.Success
        assertFalse(success.isFromCache)
        assertEquals(2, success.data.size)
    }

    @Test
    fun `IOException with populated cache returns cached data`() = runTest {
        val call = mockk<Call<List<ExpenseDto>>>()
        every { apiService.getExpenses() } returns call
        every { call.execute() } throws IOException("No network")
        coEvery { dao.getAll() } returns sampleEntities

        val result = repository.getExpenses()

        assertTrue(result is Resource.Success)
        val success = result as Resource.Success
        assertTrue(success.isFromCache)
        assertEquals(2, success.data.size)
    }

    @Test
    fun `IOException with empty cache returns error`() = runTest {
        val call = mockk<Call<List<ExpenseDto>>>()
        every { apiService.getExpenses() } returns call
        every { call.execute() } throws IOException("No network")
        coEvery { dao.getAll() } returns emptyList()

        val result = repository.getExpenses()

        assertTrue(result is Resource.Error)
        assertTrue((result as Resource.Error).message.contains("No internet"))
    }

    @Test
    fun `HTTP error with populated cache returns cached data`() = runTest {
        val call = mockk<Call<List<ExpenseDto>>>()
        every { apiService.getExpenses() } returns call
        every { call.execute() } returns Response.error(500, "".toResponseBody())
        coEvery { dao.getAll() } returns sampleEntities

        val result = repository.getExpenses()

        assertTrue(result is Resource.Success)
        assertTrue((result as Resource.Success).isFromCache)
    }
}
