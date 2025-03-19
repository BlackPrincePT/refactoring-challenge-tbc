package com.example.challenge.di

import com.example.challenge.BuildConfig
import com.example.challenge.data.network.interceptors.AuthenticationInterceptor
import com.example.challenge.data.network.service.ConnectionsService
import com.example.challenge.data.network.service.LogInService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideLogInService(builder: Retrofit.Builder): LogInService {
        return builder
            .build()
            .create(LogInService::class.java)
    }

    @Singleton
    @Provides
    fun provideConnectionsService(builder: Retrofit.Builder): ConnectionsService {
        return builder
            .build()
            .create(ConnectionsService::class.java)
    }

    @Provides
    fun provideRetrofitClient(okHttpClient: OkHttpClient): Retrofit.Builder {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))

        return retrofitBuilder
    }

    @Provides
    fun provideOkHttpClient(
        authenticationInterceptor: AuthenticationInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authenticationInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()

        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return interceptor
    }
}