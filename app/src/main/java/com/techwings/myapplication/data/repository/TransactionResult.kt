package com.techwings.myapplication.data.repository

sealed class TransactionResult<out T> {
    data class Success<T>(val data: T) : TransactionResult<T>()
    data class Error(val throwable: Throwable): TransactionResult<Nothing>()
}
