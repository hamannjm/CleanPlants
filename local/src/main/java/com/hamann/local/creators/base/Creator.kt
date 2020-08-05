package com.hamann.local.creators.base

interface Creator<in Entity, out RealmObj> {
    fun create(entity: Entity): RealmObj
}