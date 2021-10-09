package com.example.usersroom.ui.screens.add_user_screen

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.usersroom.data.room.user.Address
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

    val image = mutableStateOf<Bitmap?>(null)
    val showDialog = mutableStateOf(false)

    fun showImagePickerDialog() {
        showDialog.value = true
    }

    fun hideImagePickerDialog() {
        showDialog.value = false
    }

    private fun validInput(context: Context): Boolean {
        val valid =
            !((firstName.value.isEmpty() || lastName.value.isEmpty() || age.value.isEmpty() || streetName.value.isEmpty() || streetNumber.value.isEmpty()))
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
            val address = Address(
                streetName = streetName.value,
                streetNumber = streetNumber.value.toInt()
            )
            val user = User(0,
                firstName = firstName.value,
                lastName = lastName.value,
                age = age.value.toInt(),
                address = address
            )
            viewModelScope.launch(Dispatchers.IO) {
                addUserUsecase(user)
            }
            navController.popBackStack(Screens.UserListing.route, inclusive = false)
        }
    }

    fun setImageFromGallery(uri: Uri) {
        val source = ImageDecoder.createSource(context.contentResolver, uri)
        val bitmapImage = ImageDecoder.decodeBitmap(source)
        image.value = bitmapImage
        hideImagePickerDialog()
    }

    fun setImageFromCamera(it: Bitmap) {
        image.value = it
        hideImagePickerDialog()
    }

    val firstName = mutableStateOf("")
    val lastName = mutableStateOf("")
    val age = mutableStateOf("")
    val streetName = mutableStateOf("")
    val streetNumber = mutableStateOf("")
}