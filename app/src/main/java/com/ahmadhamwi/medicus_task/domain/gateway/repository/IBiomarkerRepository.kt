package com.ahmadhamwi.medicus_task.domain.gateway.repository

import com.ahmadhamwi.medicus_task.domain.entity.BiomarkerEntity
import com.ahmadhamwi.medicus_task.presentation.model.BiomarkerUI
import kotlinx.coroutines.flow.Flow


/**
 * Repository Pattern:
 * This interface is a "Repository" for out entity "Biomarker".
 * The Repository is responsible of implementing ONE of the following operations: (Create, Read, Update, Delete)
 * aka "CRUD" operations on the "Biomarker" entity.
 * The Repository Interface does not care what data source is dealing with.
 * The Implementation of this interface decides the data sources that should be dealt with in order
 * to get the data about a "Biomarker".
 * Popular data sources of an entity are local cache data sources, remote data sources, and
 * persistent data sources.
 */
interface IBiomarkerRepository {

    /**
     * Our Repository gets a list of biomarker entity from some datasource.
     *
     * This method is considered as a "Read" method on some data source.
     *
     * We can implement this method by reading from a persistent data source solution, like a local database for example.
     *
     * Or maybe we could implement this method by reading the data from a remote database, like using
     * an http method in order to get the data from an api.
     */
    suspend fun getBiomarkers(): Flow<List<BiomarkerEntity>>

    /**
     * Our Repository gets a biomarker entity if we provided an id.
     *
     * This method can be consider as a "Read" method on some data source.
     */
    suspend fun getBiomarkerById(id: Int): Flow<BiomarkerEntity>
}