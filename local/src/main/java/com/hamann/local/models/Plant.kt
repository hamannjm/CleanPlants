package com.hamann.local.models

import com.hamann.domain.entities.PlantEntity
import com.hamann.local.models.delegates.RealmPropertyDelegate
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

class Plant internal constructor(private val plantModel: PlantModel): PlantEntity {
    override var identifier: String? by RealmPropertyDelegate(
        plantModel,
        plantModel.identifier
    )
    override var species: String? by RealmPropertyDelegate(
        plantModel,
        plantModel.species
    )
    override var commonName: String? by RealmPropertyDelegate(
        plantModel,
        plantModel.commonName
    )
}

internal open class PlantModel(): RealmObject(), PlantEntity {
    @PrimaryKey
    override var identifier: String? = null
    override var species: String? = null
    override var commonName: String? = null
}