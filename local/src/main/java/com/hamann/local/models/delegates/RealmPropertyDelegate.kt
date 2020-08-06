package com.hamann.local.models.delegates

import com.hamann.domain.entities.base.BaseEntity
import io.realm.RealmObject
import kotlin.reflect.KProperty

class RealmPropertyDelegate<Prop>(
    private val realmObject: RealmObject,
    private var backingProp: Prop?) {

    operator fun getValue(thisRef: BaseEntity, property: KProperty<*>): Prop? {
        return backingProp
    }

    operator fun setValue(thisRef: BaseEntity, property: KProperty<*>, value: Prop?) {
        if (realmObject.isManaged) {
            realmObject.realm.executeTransaction{
                backingProp = value
            }
        } else {
            backingProp = value
        }
    }
}