package com.justcircleprod.hhcloneem.common

sealed class Resource<T>(val data: T? = null, val error: Boolean = false) {
    class Success<T>(data: T) : Resource<T>(data = data)
    class Error<T>(error: Boolean = true) : Resource<T>(error = error)
    class Loading<T> : Resource<T>()
}