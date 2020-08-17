package com.hamann.domain.plants.models

import com.hamann.domain.entities.PlantEntity

class MockPlantModel(id: String): PlantEntity {
    override val identifier: String = id
    override var species: String? = null
    override var commonName: String? = null
}