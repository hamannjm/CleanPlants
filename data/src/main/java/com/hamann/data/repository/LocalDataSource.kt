package com.hamann.data.repository

import com.hamann.domain.entities.PlantEntity
import io.reactivex.Observable

interface LocalDataSource {
    fun getPlant(identifier: String): Observable<PlantEntity>
    fun createPlant(): Observable<PlantEntity>
}