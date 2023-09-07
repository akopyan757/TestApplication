package com.techwings.myapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.techwings.myapplication.data.dto.Transaction

@Database(entities = [Transaction::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}