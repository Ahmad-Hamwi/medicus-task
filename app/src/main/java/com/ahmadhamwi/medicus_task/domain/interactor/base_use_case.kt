package com.ahmadhamwi.medicus_task.domain.interactor

import kotlinx.coroutines.flow.Flow

abstract class FlowUseCase<RETURN> {
    abstract suspend fun execute(): Flow<RETURN>
}

abstract class ParamFlowUseCase<PARAM, RETURN> {
    abstract suspend fun execute(param: PARAM): Flow<RETURN>
}