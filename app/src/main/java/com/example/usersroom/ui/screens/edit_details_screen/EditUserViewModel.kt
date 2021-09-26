package com.example.usersroom.ui.screens.edit_details_screen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.usersroom.data.room.user.User
import com.example.usersroom.domain.usecase.UpdateUserUsecase
import com.example.usersroom.ui.screens.Screens
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EditUserViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val context: Context,
    val updateUserUsecase: UpdateUserUsecase,
) : ViewModel() {

    lateinit var navControler: NavController

    companion object {
        const val TAG = "EditUserViewModel"
    }

    val firstName = mutableStateOf("")
    val lastName = mutableStateOf("")
    val age = mutableStateOf("")
    private val id: Int


    private fun validInput(context: Context): Boolean {
        val valid =
            !(firstName.value.isEmpty() || lastName.value.isEmpty() || age.value.isEmpty())
        if (!valid) {
            Toast.makeText(
                context,
                "Please fill all fields",
                Toast.LENGTH_SHORT
            ).show()
        }
        return valid
    }


    fun updateUserDetails() {
        if (validInput(context)) {
            val user = User(id, firstName.value, lastName.value, age.value.toInt())
            Log.i(TAG, user.toString())
            viewModelScope.launch(Dispatchers.IO) {
                updateUserUsecase(user)
            }
            navControler.popBackStack(Screens.UserListing.route, inclusive = false)
        }
    }

    init {
        savedStateHandle.get<String>("user").let { userJson ->
            val convertedUser = Gson().fromJson(userJson, User::class.java)
            firstName.value = convertedUser.firstName
            lastName.value = convertedUser.lastName
            age.value = convertedUser.age.toString()
            id = convertedUser.id
        }
    }
}