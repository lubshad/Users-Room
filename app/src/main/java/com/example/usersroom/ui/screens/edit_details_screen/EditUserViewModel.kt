package com.example.usersroom.ui.screens.edit_details_screen

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.usersroom.data.room.user.Address
import com.example.usersroom.data.room.user.User
import com.example.usersroom.domain.usecase.DeleteUserUsecase
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
    @SuppressLint("StaticFieldLeak") val context: Context,
    val updateUserUsecase: UpdateUserUsecase,
    val deleteUserUsecase: DeleteUserUsecase,
) : ViewModel() {

    lateinit var navControler: NavController

    companion object {
        const val TAG = "EditUserViewModel"
    }

    val showDialog = mutableStateOf(false)

    val image = mutableStateOf<Bitmap?>(null)

    fun dismissDialog() {
        Log.i(TAG, "dismissed")
        showDialog.value = false
    }

    fun showDialog() {
        showDialog.value = true
    }

    fun showImagePickerDialog() {
        showDialog.value = true
    }

    fun hideImagePickerDialog() {
        showDialog.value = false
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


    fun deleteUser() {
        val address = Address(
            streetName = streetName.value,
            streetNumber =streetNumber.value.toInt()
        )
        val user = User(id, firstName.value, lastName.value, age.value.toInt(), address = address, userImage = image.value!!)
        viewModelScope.launch {
            deleteUserUsecase(user)
        }
        navControler.popBackStack(Screens.UserListing.route, inclusive = false)
    }

    val firstName = mutableStateOf("")
    val lastName = mutableStateOf("")
    val age = mutableStateOf("")
    private val id: Int
    val streetName = mutableStateOf("")
    val streetNumber = mutableStateOf("")


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
            val address = Address(
                streetName = streetName.value,
                streetNumber =streetNumber.value.toInt()
            )
            val user = User(id, firstName.value, lastName.value, age.value.toInt(), address = address, userImage = image.value!!)
            Log.i(TAG, user.toString())
            viewModelScope.launch(Dispatchers.IO) {
                updateUserUsecase(user)
            }
            dismissDialog()
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
            streetName.value = convertedUser.address.streetName
            streetNumber.value = convertedUser.address.streetNumber.toString()
            image.value = convertedUser.userImage
        }
    }
}