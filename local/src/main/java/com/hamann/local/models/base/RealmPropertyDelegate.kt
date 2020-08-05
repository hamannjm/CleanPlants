package com.hamann.local.models.base

import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.kotlin.isManaged
import kotlin.reflect.KProperty

class RealmPropertyDelegate<Prop>(
    private val realm: Realm,
    private var backingProp: Prop?) {

    operator fun getValue(thisRef: RealmObject, property: KProperty<*>): Prop? {
        return backingProp
    }

    operator fun setValue(thisRef: RealmObject, property: KProperty<*>, value: Prop?) {
        if (backingProp != value) {
            if (thisRef.isManaged()) {
                realm.executeTransaction{
                    backingProp = value
                }
            } else {
                backingProp = value
            }
        }
    }
}