package com.hamann.plants.models

import androidx.databinding.BaseObservable
import com.hamann.domain.entities.PlantEntity
import com.hamann.plants.models.delegates.BindableEntityDelegate

class PlantBindable(
    private val entity: PlantEntity
): BaseObservable(), PlantEntity {
    override val identifier: String = entity.identifier
    override var species: String? by BindableEntityDelegate(-1, entity.species)
    override var commonName: String? by BindableEntityDelegate(-1, entity.commonName)
}