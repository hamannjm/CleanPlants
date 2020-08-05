package com.hamann.local.creators

import com.hamann.domain.entities.PlantEntity
import com.hamann.local.creators.base.Creator
import com.hamann.local.models.Plant

class PlantCreator: Creator<PlantEntity, Plant> {
    override fun create(entity: PlantEntity): Plant {
        return Plant().also {
            it.identifier = entity.identifier
            it.commonName = entity.commonName
            it.species = entity.species
        }
    }
}