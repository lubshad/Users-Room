package com.example.usersroom.common

sealed class Resource<T>(data: T? = null, error: AppError? = null) {
    class Loading<T>(): Resource<T>()
    class Error<T>(error: AppError): Resource<T>(error = error)
    class Success<T>(data: T): Resource<T>(data =data)
}
