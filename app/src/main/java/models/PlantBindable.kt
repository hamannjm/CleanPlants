package models

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.hamann.domain.entities.PlantEntity
import models.delegates.BindableEntityDelegate

class PlantBindable(
    private val entity: PlantEntity
): BaseObservable(), PlantEntity {

    override var identifier: String? by BindableEntityDelegate(-1,entity.identifier)
    override var species: String? by BindableEntityDelegate(-1, entity.species)
    override var commonName: String? by BindableEntityDelegate(-1, entity.commonName)
}