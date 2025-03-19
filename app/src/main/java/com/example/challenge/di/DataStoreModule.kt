package com.example.challenge.di

import com.example.challenge.data.local.DataStoreManager
import com.example.challenge.domain.cache.CacheManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreModule {

    @Binds
    abstract fun provideDatastoreManager(dataStoreManager: DataStoreManager): CacheManager
}
