package com.hamann.domain.entities

import com.hamann.domain.entities.base.BaseEntity

interface PlantEntity: BaseEntity {
    var identifier: String?
    var species: String?
    var commonName: String?
}