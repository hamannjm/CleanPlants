package com.hamann.domain.usecases

import com.hamann.domain.entities.PlantEntity
import com.hamann.domain.repository.PlantRepository
import com.hamann.domain.usecases.base.ObservableUseCase
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

class GetPlantListingTask @Inject constructor(
    private val repository: PlantRepository,
    background: Scheduler,
    foreground: Scheduler
): ObservableUseCase<PlantEntity, GetPlantListingTask.Params>(background, foreground) {

    override fun generateObservable(input: Params?): Observable<PlantEntity> {
        return repository.getAllPlants()
    }

    data class Params(val nothing: Nothing)
}