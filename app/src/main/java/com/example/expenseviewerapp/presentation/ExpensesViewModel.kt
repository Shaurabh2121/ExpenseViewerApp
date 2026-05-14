package com.example.expenseviewerapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expenseviewerapp.domain.usecase.GetExpensesUseCase
import com.example.expenseviewerapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpensesViewModel @Inject constructor(
    private val getExpensesUseCase: GetExpensesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ExpensesUiState())
    val uiState: StateFlow<ExpensesUiState> = _uiState.asStateFlow()

    init {
        loadExpenses()
    }

    fun retry() {
        loadExpenses()
    }

    private fun loadExpenses() {
        viewModelScope.launch {
            _uiState.value = ExpensesUiState(isLoading = true)
            when (val result = getExpensesUseCase()) {
                is Resource.Success -> {
                    _uiState.value = ExpensesUiState(
                        expenses = result.data,
                        isShowingCachedData = result.isFromCache
                    )
                }
                is Resource.Error -> {
                    _uiState.value = ExpensesUiState(
                        errorMessage = result.message
                    )
                }
                is Resource.Loading -> Unit
            }
        }
    }
}
