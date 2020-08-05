package com.hamann.domain.usecases

import com.hamann.domain.entities.PlantEntity
import com.hamann.domain.repository.PlantRepository
import com.hamann.domain.usecases.base.ObservableUseCase
import io.reactivex.Observable
import io.reactivex.Scheduler
import java.lang.IllegalArgumentException

class SavePlantUseCase(
    private val repository: PlantRepository,
    background: Scheduler,
    foreground: Scheduler
): ObservableUseCase<PlantEntity, SavePlantUseCase.params>(background, foreground) {

    override fun generateObservable(input: params?): Observable<PlantEntity> {
        if (input?.plant == null) {
            throw IllegalArgumentException("Must provide a plant to save!")
        }
        return repository.createPlant(input.plant)
    }

    data class params(val plant: PlantEntity? = null)
}