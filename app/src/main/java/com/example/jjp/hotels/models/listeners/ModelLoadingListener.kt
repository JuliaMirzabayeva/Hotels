package com.example.jjp.hotels.models.listeners

interface ModelLoadingListener<T> {
    fun onModelLoaded(model: T)

    fun onModelError(error : String?)

    fun onModelFailure(error: Throwable?)
}