package com.sameh.estatehub.domain.resourceloader

sealed class ResourceState<T> {
    class Loading<T> : ResourceState<T>()
    data class Success<T>(val data: T) : ResourceState<T>()
    data class Error<T>(val message: String) : ResourceState<T>()

    inline fun <reified R> mapSuccess(transform: (T) -> R): ResourceState<R> {
        return when (this) {
            is Loading -> Loading()
            is Success -> Success(transform(data))
            is Error -> Error(message)
        }
    }
}