package com.hamann.local.plants.models

import com.hamann.domain.entities.PlantEntity

class TestPlantEntityImpl: PlantEntity {
    override var identifier: String? = null
    override var species: String? = null
    override var commonName: String? = null
}