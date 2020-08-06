package com.hamann.local.creators

import com.hamann.domain.entities.PlantEntity
import com.hamann.local.creators.base.Creator
import com.hamann.local.models.Plant
import com.hamann.local.models.PlantModel
import io.realm.Realm
import java.util.*

class PlantCreator: Creator<Plant> {
    private val realm = Realm.getDefaultInstance()
    override fun create(): Plant {
        val plantModel = PlantModel()
        plantModel.identifier = UUID.randomUUID().toString()
        realm.beginTransaction()
        val managedPlant = realm.copyToRealm(plantModel)
        realm.commitTransaction()
        return Plant(managedPlant)
    }
}