package com.hamann.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hamann.domain.entities.PlantEntity
import com.hamann.domain.usecases.GetPlantTask
import com.hamann.presentation.models.Resource
import javax.inject.Inject

class PlantVM @Inject internal constructor(
    private val getPlantTask: GetPlantTask
): ViewModel(){

}