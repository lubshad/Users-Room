package com.example.usersroom.domain.repository

import androidx.lifecycle.LiveData
import com.example.usersroom.data.room.user.User

interface UserRepository {
    fun getAllUsers() :LiveData<List<User>>

    suspend fun addUser(user: User)
    suspend fun updateUser(user: User)
}