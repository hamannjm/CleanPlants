package com.hamann.local.models

import com.hamann.domain.entities.PlantEntity
import com.hamann.local.models.delegates.RealmPropertyDelegate
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

class Plant internal constructor(plantModel: PlantModel): PlantEntity {
    override val identifier: String = plantModel.identifier ?: throw Throwable("Must provide primary key!")
    override var species: String? by RealmPropertyDelegate(
        plantModel,
        plantModel.species
    )
    override var commonName: String? by RealmPropertyDelegate(
        plantModel,
        plantModel.commonName
    )
}

internal open class PlantModel(): RealmObject() {
    @PrimaryKey
    var identifier: String? = null
    var species: String? = null
    var commonName: String? = null
}