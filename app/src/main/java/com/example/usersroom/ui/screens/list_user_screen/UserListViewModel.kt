package com.example.usersroom.ui.screens.list_user_screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usersroom.domain.repository.UserRepository
import com.example.usersroom.domain.usecase.DeleteAllUserUsecase
import com.example.usersroom.ui.screens.edit_details_screen.EditUserViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userRepository: UserRepository,
    val deleteAllUserUsecase: DeleteAllUserUsecase,
) : ViewModel() {
    companion object {
        const val TAG = "UserListViewModel"
    }

    val userList = userRepository.getAllUsers()


    val showDialog = mutableStateOf(false)

    fun dismissDialog() {
        Log.i(EditUserViewModel.TAG, "dismissed")
        showDialog.value = false
    }

    fun showDialog() {
        showDialog.value = true
    }


    fun deleteUser() {
        viewModelScope.launch {
            deleteAllUserUsecase()
        }
        dismissDialog()
    }

//
//    init {
//       val userList =  userRepository.getAllUsers()
//        userList.value?.forEach {
//            Log.i(TAG, it.firstName)
//        }
//    }
}