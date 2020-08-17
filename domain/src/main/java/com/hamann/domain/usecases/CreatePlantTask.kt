package com.hamann.domain.usecases

import com.hamann.domain.entities.PlantEntity
import com.hamann.domain.repository.PlantRepository
import com.hamann.domain.usecases.base.ObservableUseCase
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

class CreatePlantTask @Inject constructor(
    private val repository: PlantRepository,
    background: Scheduler,
    foreground: Scheduler
): ObservableUseCase<PlantEntity, CreatePlantTask.Params>(background, foreground) {

    override fun generateObservable(input: Params?): Observable<PlantEntity> {
        return repository.createPlant()
    }

    data class Params(val nothing: Nothing)
}