package com.hamann.plants.dependencyinjection

import com.hamann.data.repository.LocalDataSource
import com.hamann.local.creators.PlantCreator
import com.hamann.local.source.LocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.realm.Realm
import javax.inject.Singleton

@Module(includes = [LocalModule.Binders::class])
class LocalModule {

    @Module
    interface Binders {
        @Binds
        fun bindsLocalDataSource(
            localDataSourceImpl: LocalDataSourceImpl
        ): LocalDataSource
    }

    @Provides
    @Singleton
    fun providesRealm(): Realm {
        return Realm.getDefaultInstance()
    }

    @Provides
    @Singleton
    fun providesPlantCreator(
        realm: Realm
    ): PlantCreator {
        return PlantCreator(realm)
    }
}