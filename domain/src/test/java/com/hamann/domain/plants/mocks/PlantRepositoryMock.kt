package com.hamann.domain.plants.mocks

import com.hamann.domain.entities.PlantEntity
import com.hamann.domain.plants.models.MockPlantModel
import com.hamann.domain.repository.PlantRepository
import io.reactivex.Observable
import java.util.*

class PlantRepositoryMock: PlantRepository {

    private val plants = mutableMapOf<String, PlantEntity>()

    override fun getPlant(identifier: String): Observable<PlantEntity> {
        return Observable.just(plants[identifier])
    }

    override fun getAllPlants(): Observable<PlantEntity> {
        TODO("Not yet implemented")
    }

    override fun createPlant(): Observable<PlantEntity> {
        val newPlant = MockPlantModel(UUID.randomUUID().toString())
        plants[newPlant.identifier] = newPlant
        return Observable.just(newPlant)
    }

    fun addTestData(plant: PlantEntity) {
        plants[plant.identifier] = plant
    }
}