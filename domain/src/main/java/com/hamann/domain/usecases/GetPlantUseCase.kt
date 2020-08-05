package com.hamann.domain.usecases

import com.hamann.domain.entities.PlantEntity
import com.hamann.domain.repository.PlantRepository
import com.hamann.domain.usecases.base.ObservableUseCase
import io.reactivex.Observable
import io.reactivex.Scheduler
import java.lang.IllegalArgumentException

class GetPlantUseCase(
    private val repository: PlantRepository,
    background: Scheduler,
    foreground: Scheduler): ObservableUseCase<PlantEntity, GetPlantUseCase.Params>(background, foreground) {

    override fun generateObservable(input: Params?): Observable<PlantEntity> {
        if (input == null || input.identifier == "") {
            throw IllegalArgumentException("Must provide valid params")
        }
        return repository.getPlant(input.identifier)
    }

    data class Params(val identifier: String)
}