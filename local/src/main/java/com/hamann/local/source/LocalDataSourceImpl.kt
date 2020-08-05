package com.hamann.local.source

import com.hamann.data.repository.LocalDataSource
import com.hamann.domain.entities.PlantEntity
import com.hamann.local.creators.PlantCreator
import com.hamann.local.models.Plant
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.realm.Realm
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val realm: Realm,
    private val plantCreator: PlantCreator
): LocalDataSource {

    override fun getPlant(identifier: String): Observable<PlantEntity> {
        val result = realm.where(Plant::class.java).equalTo(Plant::identifier.name, identifier).findFirstAsync()
        return Observable.create { emitter: ObservableEmitter<PlantEntity> ->
            result.addChangeListener<Plant> { foundPlant ->
                emitter.onNext(foundPlant)
                emitter.onComplete()
            }
        }
    }

    override fun createPlant(plant: PlantEntity): Observable<PlantEntity> {
        val realmPlant = plantCreator.create(plant)
        realm.executeTransaction {
            it.insert(realmPlant)
        }
        return Observable.just(realmPlant)
    }
}