package com.hamann.local.creators

import com.hamann.local.creators.base.Creator
import com.hamann.local.models.Plant
import com.hamann.local.models.PlantModel
import io.realm.Realm
import java.util.*
import javax.inject.Inject

class PlantCreator @Inject constructor(
    private val realm: Realm
): Creator<Plant> {
    override fun create(): Plant {
        val managedPlant = newManagedPlant()
        return Plant(managedPlant)
    }

    internal fun newManagedPlant(identifier: String? = null): PlantModel {
        val key = identifier ?: UUID.randomUUID().toString()
        realm.beginTransaction()
        val managedPlant = realm.createObject(PlantModel::class.java, key)
        realm.commitTransaction()
        return managedPlant
    }
}