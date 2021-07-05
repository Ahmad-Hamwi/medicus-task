package com.ahmadhamwi.medicus_task.infrastructure.datasource

import com.ahmadhamwi.medicus_task.domain.entity.BiomarkerEntity
import com.ahmadhamwi.medicus_task.presentation.model.BiomarkerUI
import kotlinx.coroutines.flow.Flow

interface IBiomarkerRemoteDatasource {
    suspend fun getBiomarkers(): Flow<List<BiomarkerEntity>>
    suspend fun getBiomarkerById(id: Int): Flow<BiomarkerEntity>
}