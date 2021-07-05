package com.ahmadhamwi.medicus_task.domain.interactor

import com.ahmadhamwi.medicus_task.domain.entity.BiomarkerEntity
import com.ahmadhamwi.medicus_task.domain.gateway.repository.IBiomarkerRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.delayEach
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class GetBiomarkersUseCase @Inject constructor(
    private val biomarkRepository: IBiomarkerRepository
) : FlowUseCase<List<BiomarkerEntity>>() {

    override suspend fun execute(): Flow<List<BiomarkerEntity>> {
        return biomarkRepository.getBiomarkers().onEach { delay(1000) }
    }
}