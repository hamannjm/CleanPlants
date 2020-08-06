package com.hamann.domain.repository

import com.hamann.domain.entities.PlantEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface PlantRepository {
    fun getPlant(identifier: String): Observable<PlantEntity>
    fun createPlant(): Observable<PlantEntity>
}