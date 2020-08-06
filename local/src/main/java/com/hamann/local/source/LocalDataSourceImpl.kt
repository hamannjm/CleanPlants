package com.hamann.local.source

import com.hamann.data.repository.LocalDataSource
import com.hamann.domain.entities.PlantEntity
import com.hamann.local.creators.PlantCreator
import com.hamann.local.models.Plant
import com.hamann.local.models.PlantModel
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.realm.Realm
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val realm: Realm,
    private val plantCreator: PlantCreator
): LocalDataSource {

    override fun getPlant(identifier: String): Observable<PlantEntity> {
        val result = realm.where(PlantModel::class.java).equalTo(Plant::identifier.name, identifier).findFirstAsync()
        return Observable.create { emitter: ObservableEmitter<PlantEntity> ->
            result.addChangeListener<PlantModel> { foundPlant ->
                emitter.onNext(Plant(foundPlant))
                emitter.onComplete()
            }
        }
    }

    override fun createPlant(): Observable<PlantEntity> {
        val realmPlant = plantCreator.create()
        return Observable.just(realmPlant)
    }
}