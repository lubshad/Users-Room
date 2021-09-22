package com.example.usersroom.common

sealed class AppError(message: String) {
    class NetworkError(message: String = "Please check your network connection") : AppError(message)
    class DatabaseError(message: String = "Unexpected Error occurred, can't reach database") :
        AppError(message)
    class ServerError(message: String = "Unexpected Error occurred in server") : AppError(message)
    class UnexpectedError(message: String = "Unexpected Error occurred") : AppError(message)


    fun errorMessage(): String {
        return when (this) {
            is DatabaseError -> "Unexpected Error occurred, can't reach database"
            is NetworkError -> "Please check your network connection"
            is ServerError -> "Unexpected Error occurred in server"
            is UnexpectedError -> "Unexpected Error occurred"
        }
    }
}
