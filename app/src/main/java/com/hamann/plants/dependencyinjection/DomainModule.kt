package com.hamann.plants.dependencyinjection

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    fun providesForeground(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    @Provides
    @Singleton
    fun providesBackground(): Scheduler {
        return Schedulers.io()
    }
}