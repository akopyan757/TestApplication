package com.techwings.myapplication.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.techwings.myapplication.data.dto.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Query("SELECT * FROM transactions")
    fun getAll(): Flow<List<Transaction>>

    @Query("SELECT * FROM transactions WHERE uid = :id LIMIT 1")
    fun getById(id: Long): Transaction?

    @Insert
    fun insert(data: Transaction)
}