package com.hamann.local.source

import com.hamann.data.repository.LocalDataSource
import com.hamann.domain.entities.PlantEntity
import com.hamann.local.creators.PlantCreator
import com.hamann.local.models.Plant
import com.hamann.local.models.PlantModel
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.Scheduler
import io.realm.Realm
import io.realm.rx.ObjectChange
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val realm: Realm,
    private val plantCreator: PlantCreator,
    private val background: Scheduler
): LocalDataSource {

    override fun getPlant(identifier: String): Observable<PlantEntity> {
        return realm.where(PlantModel::class.java).equalTo(Plant::identifier.name, identifier).findFirstAsync()
            .asChangesetObservable<PlantModel>()
            .observeOn(background)
            .filter { change -> change.`object`.isLoaded }
            .map { loadedChange -> loadedChange.`object` as PlantEntity }
    }

    override fun getAllPlants(): Observable<PlantEntity> {
        val results = realm.where(PlantModel::class.java).findAllAsync()
        return Observable.create { emitter: ObservableEmitter<PlantEntity> ->
            results.forEach { plant ->
                emitter.onNext(plant)
            }
            emitter.onComplete()
        }
    }

    override fun createPlant(): Observable<PlantEntity> {
        val realmPlant = plantCreator.create()
        return Observable.just(realmPlant)
    }
}