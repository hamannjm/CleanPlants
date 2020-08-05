package com.hamann.local.models

import com.hamann.domain.entities.PlantEntity
import com.hamann.local.models.base.RealmPropertyDelegate
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Plant: RealmObject(), PlantEntity {
    @PrimaryKey
    override var identifier: String? = null
    override var species: String? by RealmPropertyDelegate(realm, species)
    override var commonName: String? by RealmPropertyDelegate(realm, commonName)
}