package com.example.expenseviewerapp.util

sealed class Resource<out T> {
    data class Success<T>(val data: T, val isFromCache: Boolean = false) : Resource<T>()
    data class Error(val message: String, val throwable: Throwable? = null) : Resource<Nothing>()
    data object Loading : Resource<Nothing>()
}
