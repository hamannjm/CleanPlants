package com.hamann.local.source.base

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.kotlin.where

abstract class BaseLocalDataSource<in RealmObj: RealmObject, Entity>(
    private val realm: Realm,
    private val background: Scheduler
) {
}