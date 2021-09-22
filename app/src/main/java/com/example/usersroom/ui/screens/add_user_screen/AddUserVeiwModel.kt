package com.example.usersroom.ui.screens.add_user_screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AddUserViewModel : ViewModel() {
    companion object {
        const val TAG = "AddUserViewModel"
    }

    fun addUser() {
        Log.i(TAG, "${firstName.value}, ${lastName.value} , ${age.value}")
    }

    val firstName = mutableStateOf("")
    val lastName = mutableStateOf("")
    val age = mutableStateOf("")
}