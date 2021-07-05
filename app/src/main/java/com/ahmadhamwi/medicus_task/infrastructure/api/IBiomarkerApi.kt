package com.ahmadhamwi.medicus_task.infrastructure.api

import com.ahmadhamwi.medicus_task.infrastructure.BIOMARKERS_ENDPOINT
import com.ahmadhamwi.medicus_task.infrastructure.BIOMARKER_ENDPOINT
import com.ahmadhamwi.medicus_task.infrastructure.BIOMARKER_ID_PATH_PARAM
import com.ahmadhamwi.medicus_task.infrastructure.api.model.BiomarkerApiModel
import retrofit2.Response
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Path

interface IBiomarkerApi {

    @GET(BIOMARKERS_ENDPOINT)
    suspend fun getBiomarkers(): Response<List<BiomarkerApiModel>>

    @GET(BIOMARKER_ENDPOINT)
    suspend fun getBiomarkerById(@Path(BIOMARKER_ID_PATH_PARAM) id: Int): Response<BiomarkerApiModel>
}