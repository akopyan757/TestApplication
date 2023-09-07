package com.techwings.myapplication.common.di

import android.content.Context
import androidx.room.Room
import com.techwings.myapplication.data.database.AppDatabase
import com.techwings.myapplication.data.database.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = "database.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(
        roomDatabase: AppDatabase,
    ): TransactionDao {
        return roomDatabase.transactionDao()
    }
}