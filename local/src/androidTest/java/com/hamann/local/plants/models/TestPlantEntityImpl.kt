package com.hamann.local.plants.models

import com.hamann.domain.entities.PlantEntity

class TestPlantEntityImpl(id: String): PlantEntity {
    override val identifier: String = id
    override var species: String? = null
    override var commonName: String? = null
}