package com.hamann.data.repository

import com.hamann.domain.entities.PlantEntity
import com.hamann.domain.repository.PlantRepository
import io.reactivex.Observable
import javax.inject.Inject

class PlantRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
): PlantRepository {

    override fun getPlant(identifier: String): Observable<PlantEntity> {
        return localDataSource.getPlant(identifier)
    }

    override fun createPlant(): Observable<PlantEntity> {
        return localDataSource.createPlant()
    }
}