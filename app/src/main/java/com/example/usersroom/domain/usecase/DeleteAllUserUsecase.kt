package com.example.usersroom.domain.usecase


import com.example.usersroom.data.room.user.User
import com.example.usersroom.domain.repository.UserRepository
import javax.inject.Inject

class DeleteAllUserUsecase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke() {
        userRepository.deleteAllUser()
    }
}