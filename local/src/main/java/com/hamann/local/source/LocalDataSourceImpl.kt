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
import io.realm.RealmChangeListener
import io.realm.RealmResults
import io.realm.kotlin.addChangeListener
import io.realm.rx.ObjectChange
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val realm: Realm,
    private val plantCreator: PlantCreator
): LocalDataSource {

    override fun getPlant(identifier: String): Observable<PlantEntity> {
        return realm.where(PlantModel::class.java).equalTo(Plant::identifier.name, identifier).findFirstAsync()
            .asChangesetObservable<PlantModel>()
            .filter { change -> change.`object`.isLoaded }
            .map { loadedChange ->
                val managedPlant = loadedChange.`object`
                Plant(managedPlant)
            }
    }

    override fun getAllPlants(): Observable<PlantEntity> {
        return Observable.create<PlantEntity> { emitter: ObservableEmitter<PlantEntity> ->
            val results = realm.where(PlantModel::class.java).findAllAsync()
            results
                .addChangeListener(RealmChangeListener<RealmResults<PlantModel>> {
                    it.forEach { loaded ->
                        emitter.onNext(Plant(loaded))
                    }
                    emitter.onComplete()
                })
        }
    }

    override fun createPlant(): Observable<PlantEntity> {
        val realmPlant = plantCreator.create()
        return Observable.just(realmPlant)
    }
}