package com.hamann.local.creators.base

interface Creator<LocalModel> {
    fun create(): LocalModel
}