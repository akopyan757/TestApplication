package com.techwings.myapplication.data.repository

import com.techwings.myapplication.data.database.TransactionDao
import com.techwings.myapplication.data.dto.Transaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val dao: TransactionDao
): TransactionRepository {

    override fun getAllTransactions(): Flow<List<Transaction>> {
        return dao.getAll().flowOn(Dispatchers.IO)
    }

    override fun getTransactionById(id: Long) = flow {
        try {
            val transaction = dao.getById(id)
            if (transaction != null) {
                emit(TransactionResult.Success(transaction))
            } else {
                emit(TransactionResult.Error(Throwable("Transaction not found")))
            }
        } catch (throwable: Throwable) {
            emit(TransactionResult.Error(throwable))
        }
    }.flowOn(Dispatchers.IO)

    override fun createTransaction(transaction: Transaction) = flow {
        try {
            dao.insert(transaction)
            emit(TransactionResult.Success(Unit))
        } catch (throwable: Throwable) {
            emit(TransactionResult.Error(throwable))
        }
    }.flowOn(Dispatchers.IO)
}