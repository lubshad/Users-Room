package com.example.usersroom.data.respository

import androidx.lifecycle.LiveData
import com.example.usersroom.data.room.user.User
import com.example.usersroom.data.room.user.UserDao
import com.example.usersroom.domain.repository.UserRepository

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {
    override fun getAllUsers(): LiveData<List<User>> {
        return userDao.getAllUsers()
    }

    override suspend fun addUser(user: User) {
        userDao.addUser(user)
    }
}