package com.example.usersroom.ui.screens.list_user_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.usersroom.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {
companion object {
    const val TAG = "UserListViewModel"
}

    val userList = userRepository.getAllUsers()

//
//    init {
//       val userList =  userRepository.getAllUsers()
//        userList.value?.forEach {
//            Log.i(TAG, it.firstName)
//        }
//    }
}