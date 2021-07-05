package com.ahmadhamwi.medicus_task.domain.interactor

import com.ahmadhamwi.medicus_task.domain.entity.BiomarkerEntity
import com.ahmadhamwi.medicus_task.domain.gateway.repository.IBiomarkerRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetBiomarkerUseCase @Inject constructor(val biomarkerRepository: IBiomarkerRepository) :
    ParamFlowUseCase<GetBiomarkerUseCase.GetBiomarkerUseCaseParam, BiomarkerEntity>() {

    /**
     * Our "Getting a biomarker by id" business logic here.
     * Maybe our system requires saving the entity once we've got it, these decisions are considered
     * as business logic, and we should be handling them here.
     */
    override suspend fun execute(param: GetBiomarkerUseCaseParam): Flow<BiomarkerEntity> {
        return biomarkerRepository.getBiomarkerById(param.biomarkerId).onEach { delay(1000) }
    }

    class GetBiomarkerUseCaseParam(val biomarkerId: Int)
}