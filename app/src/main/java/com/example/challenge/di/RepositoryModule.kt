package com.example.challenge.di

import com.example.challenge.data.repository.ConnectionsRepositoryImpl
import com.example.challenge.data.repository.LogInRepositoryImpl
import com.example.challenge.domain.repository.ConnectionsRepository
import com.example.challenge.domain.repository.LogInRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideConnectionsRepository(repository: ConnectionsRepositoryImpl): ConnectionsRepository

    @Binds
    abstract fun provideLoginRepository(repository: LogInRepositoryImpl): LogInRepository
}