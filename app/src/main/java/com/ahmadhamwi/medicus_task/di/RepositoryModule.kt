package com.ahmadhamwi.medicus_task.di

import com.ahmadhamwi.medicus_task.domain.gateway.repository.IBiomarkerRepository
import com.ahmadhamwi.medicus_task.infrastructure.repo.BiomarkerRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindBiomarkerRepository(repo: BiomarkerRepositoryImpl): IBiomarkerRepository
}