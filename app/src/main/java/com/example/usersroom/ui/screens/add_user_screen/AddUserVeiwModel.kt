package com.example.usersroom.ui.screens.add_user_screen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import com.example.usersroom.data.room.user.User
import com.example.usersroom.domain.usecase.AddUserUsecase
import com.example.usersroom.ui.screens.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddUserViewModel @Inject constructor(
    val addUserUsecase: AddUserUsecase,
    val context: Context,
) : ViewModel() {

    lateinit var navController: NavController

    companion object {
        const val TAG = "AddUserViewModel"
    }

    private fun validInput(context: Context): Boolean {
        val valid =
            !((firstName.value.isEmpty() || lastName.value.isEmpty() || age.value.isEmpty()))
        if (!valid) {
            Toast.makeText(
                context,
                "Please fill all fields",
                Toast.LENGTH_SHORT
            ).show()
        }
        return valid
    }

    fun addUser() {
        if (validInput(context)) {
            Log.i(TAG, "${firstName.value}, ${lastName.value} , ${age.value}")
            val user = User(0,
                firstName = firstName.value,
                lastName = lastName.value,
                age = age.value.toInt()
            )
            viewModelScope.launch(Dispatchers.IO) {
                addUserUsecase(user)
            }
            navController.popBackStack(Screens.UserListing.route, inclusive = false)
        }
    }

    val firstName = mutableStateOf("")
    val lastName = mutableStateOf("")
    val age = mutableStateOf("")
}