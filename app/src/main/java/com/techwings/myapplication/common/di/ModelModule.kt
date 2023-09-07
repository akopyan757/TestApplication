package com.techwings.myapplication.common.di

import com.techwings.myapplication.data.repository.TransactionRepository
import com.techwings.myapplication.data.repository.TransactionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ModelModule {

    @Binds
    abstract fun provideRepository(repo: TransactionRepositoryImpl): TransactionRepository
}