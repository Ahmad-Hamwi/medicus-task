package com.ahmadhamwi.medicus_task.infrastructure.repo

import com.ahmadhamwi.medicus_task.domain.entity.BiomarkerEntity
import com.ahmadhamwi.medicus_task.domain.gateway.repository.IBiomarkerRepository
import com.ahmadhamwi.medicus_task.infrastructure.datasource.IBiomarkerRemoteDatasource
import com.ahmadhamwi.medicus_task.presentation.model.BiomarkerUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository Pattern implemented here!
 */

@Singleton
class BiomarkerRepositoryImpl @Inject constructor(
    private val biomarkerRemoteDatasource: IBiomarkerRemoteDatasource
) : IBiomarkerRepository {


    /**
     * The implementation decides to get the list from an remote datasource, which is in our case, the api.
     */
    override suspend fun getBiomarkers(): Flow<List<BiomarkerEntity>> {
        return biomarkerRemoteDatasource.getBiomarkers()
    }

    /**
     * The Implementation decides to get the list from a remote datasource, which is in our case, the api.
     */
    override suspend fun getBiomarkerById(id: Int): Flow<BiomarkerEntity> {
        return biomarkerRemoteDatasource.getBiomarkerById(id)
    }
}