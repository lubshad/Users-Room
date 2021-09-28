package com.example.usersroom.domain.usecase

import com.example.usersroom.domain.repository.UserRepository
import javax.inject.Inject

class GetAllUserUsecase @Inject constructor(
    private val userRepository: UserRepository,
) {
    operator fun invoke() = userRepository.getAllUsers()
}