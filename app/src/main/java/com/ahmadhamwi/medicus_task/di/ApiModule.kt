package com.ahmadhamwi.medicus_task.di

import com.ahmadhamwi.medicus_task.infrastructure.api.IBiomarkerApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideBiomarkerApi(retrofit: Retrofit): IBiomarkerApi {
        return retrofit.create(IBiomarkerApi::class.java)
    }
}