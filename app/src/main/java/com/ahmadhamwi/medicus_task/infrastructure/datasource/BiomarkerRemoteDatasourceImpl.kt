package com.ahmadhamwi.medicus_task.infrastructure.datasource

import com.ahmadhamwi.medicus_task.domain.entity.BiomarkerEntity
import com.ahmadhamwi.medicus_task.infrastructure.api.IBiomarkerApi
import com.ahmadhamwi.medicus_task.infrastructure.api.error.NoInternetConnectionException
import com.ahmadhamwi.medicus_task.infrastructure.api.error.UnHandledResponseException
import com.ahmadhamwi.medicus_task.infrastructure.api.util.INetworkChecker
import com.ahmadhamwi.medicus_task.infrastructure.api.util.NetworkChecker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject
import kotlin.streams.toList

class BiomarkerRemoteDatasourceImpl @Inject constructor(
    private val biomarkerApi: IBiomarkerApi,
    networkChecker: INetworkChecker
) : BaseRemoteDataSource(networkChecker), IBiomarkerRemoteDatasource {
    override suspend fun getBiomarkers(): Flow<List<BiomarkerEntity>> {
        try {
            val response = biomarkerApi.getBiomarkers()

            return if (response.isSuccessful) {
                if (response.body() == null)
                    flow { emit(mutableListOf<BiomarkerEntity>()) }
                else
                    flow { emit(response.body()!!.stream().map { it.toEntity() }.toList()) }

            } else {
                throw UnHandledResponseException()
            }
        } catch (e: Exception) {
            flow { emit(e) }
        }
        return flow { emit(null as List<BiomarkerEntity>) }
    }

    override suspend fun getBiomarkerById(id: Int): Flow<BiomarkerEntity> {

        try {
            val response = biomarkerApi.getBiomarkerById(id)

            return if (response.isSuccessful) {
                if (response.body() == null)
                    flow { emit(BiomarkerEntity()) }
                else
                    flow { emit(response.body()!!.toEntity()) }

            } else {
                throw UnHandledResponseException()
            }
        } catch (e: Exception) {
            flow { emit(e) }
        }
        return flow { emit(null as BiomarkerEntity) }
    }
}