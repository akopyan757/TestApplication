package com.techwings.myapplication.data.repository

import com.techwings.myapplication.data.dto.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    fun getAllTransactions(): Flow<List<Transaction>>
    fun getTransactionById(id: Long): Flow<TransactionResult<Transaction>>
    fun createTransaction(transaction: Transaction): Flow<TransactionResult<Unit>>
}