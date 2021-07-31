package com.ck.dev.recipesapp.utils

sealed class ApiCallState<T>(
    val data: T? = null,
    var errorCode: Int? = null
) {
    class Success<T>(data: T, errorCode: Int) : ApiCallState<T>(data, errorCode)
    /*class StatusCode<T>(errorCode: T) : Resource<T>(errorCode)*/
    class Loading<T>(data: T? = null) : ApiCallState<T>(data)
    class Failure<T>(errorCode: Int) : ApiCallState<T>(null, errorCode)

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Failure -> "Error[exception=$errorCode]"
            is Loading<T> -> "Loading"

        }
    }
}