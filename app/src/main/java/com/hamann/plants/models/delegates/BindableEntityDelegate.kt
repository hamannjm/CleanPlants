package com.hamann.plants.models.delegates

import androidx.databinding.BaseObservable
import kotlin.reflect.KProperty

class BindableEntityDelegate<Prop>(
    private val br: Int,
    private var backingField: Prop?){

    operator fun getValue(thisRef: BaseObservable, property: KProperty<*>): Prop? {
        return backingField
    }

    operator fun setValue(thisRef: BaseObservable, property: KProperty<*>, value: Prop?) {
        backingField = value
        thisRef.notifyPropertyChanged(br)
    }
}