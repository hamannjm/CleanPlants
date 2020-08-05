package com.hamann.domain.usecases.base

import io.reactivex.Completable
import io.reactivex.Scheduler

abstract class CompletableUseCase<in Input> (
    private val backgroundScheduler: Scheduler,
    private val foregroundScheduler: Scheduler
) {
    abstract fun generateCompletable(input: Input? = null): Completable

    fun build(input: Input? = null): Completable {
        return generateCompletable(input)
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
    }
}