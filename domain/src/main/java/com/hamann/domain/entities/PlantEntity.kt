package com.hamann.domain.entities

import com.hamann.domain.entities.base.BaseEntity

interface PlantEntity: BaseEntity {
    val identifier: String
    var species: String?
    var commonName: String?
}