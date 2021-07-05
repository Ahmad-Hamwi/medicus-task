package com.ahmadhamwi.medicus_task.di

import com.ahmadhamwi.medicus_task.infrastructure.datasource.BiomarkerRemoteDatasourceImpl
import com.ahmadhamwi.medicus_task.infrastructure.datasource.IBiomarkerRemoteDatasource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DatasourceModule {

    @Binds
    @Singleton
    abstract fun bindBiomarkerRemoteDatasource(datasource: BiomarkerRemoteDatasourceImpl): IBiomarkerRemoteDatasource
}