package com.hamann.plants.dependencyinjection

import com.hamann.data.repository.PlantRepositoryImpl
import com.hamann.domain.repository.PlantRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {
    @Binds
    abstract fun bindsPlantRepository(
        repository: PlantRepositoryImpl
    ): PlantRepository
}