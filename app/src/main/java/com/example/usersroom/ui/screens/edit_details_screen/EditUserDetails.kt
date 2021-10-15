package com.example.usersroom.ui.screens.edit_details_screen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.usersroom.DefaultSpacer
import com.example.usersroom.ui.components.ShowAlertDialog
import com.example.usersroom.ui.components.ShowImagePickerAlertDialog


@Composable
fun EditUserDetails(
    navController: NavHostController,
    editUserViewModel: EditUserViewModel = hiltViewModel(),
) {

    editUserViewModel.navControler = navController


    val cameraImageLaucher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicturePreview()) {

            if (it != null) {

                editUserViewModel.setImageFromCamera(it)
            }
        }

    val galleryImageLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
            if (it != null) {

                editUserViewModel.setImageFromGallery(it)
            }
        }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Edit User Details") },
                actions = {
                    IconButton(onClick = {
                        editUserViewModel.showDialog()
                    }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete User")
                    }
                })

        },
    ) {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            DefaultSpacer()
                Box() {
                    editUserViewModel.image.value?.let { image ->
                        Image(
                            image.asImageBitmap(),
                            contentDescription = "avatar",
                            contentScale = ContentScale.Crop,            // crop the image if it's not a square
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape)            // add a border (optional)
                        )
                    }

                    IconButton(onClick = {
                        if (!editUserViewModel.showDialog.value) {
                            editUserViewModel.showImagePickerDialog()
                        }
                    },
                        modifier = Modifier.align(Alignment.BottomCenter)) {
                        Icon(imageVector = if (editUserViewModel.image.value == null) Icons.Default.Add else Icons.Default.Edit,
                            contentDescription = "Edit Photo",
                            tint = if (editUserViewModel.image.value == null) Color.Black else Color.White)
                    }
                }
            OutlinedTextField(value = editUserViewModel.firstName.value, onValueChange = {
                editUserViewModel.firstName.value = it
            }, label = { Text("First Name") })
            DefaultSpacer()
            OutlinedTextField(value = editUserViewModel.lastName.value, onValueChange = {
                editUserViewModel.lastName.value = it
            }, label = { Text("Last Name") })
            DefaultSpacer()
            OutlinedTextField(value = editUserViewModel.age.value,
                onValueChange = {
                    val age = it.toIntOrNull()
                    if (age != null) {
                        editUserViewModel.age.value = age.toString()
                    }
                },
                label = { Text("Age") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
            DefaultSpacer()
            OutlinedTextField(value = editUserViewModel.streetName.value, onValueChange = {
                editUserViewModel.streetName.value = it
            }, label = { Text("Street Name") })
            DefaultSpacer()
            OutlinedTextField(value = editUserViewModel.streetNumber.value, onValueChange = {
                val streetNumber = it.toIntOrNull()
                if (streetNumber != null) {
                    editUserViewModel.streetNumber.value = streetNumber.toString()
                }
            }, label = { Text("Street Number") },keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
            DefaultSpacer()
            Button(onClick = {
                editUserViewModel.updateUserDetails()

            }) {
                Text(text = "Update User")
            }
        }
    }
    if (editUserViewModel.showDialog.value ){
        ShowAlertDialog(deleteUser ={editUserViewModel.deleteUser()}, dismissDialog = {editUserViewModel.dismissDialog()}, message = "Are you sure delete this user")
    }
    if (editUserViewModel.showDialog.value) {
        ShowImagePickerAlertDialog(dismissDialog = { editUserViewModel.hideImagePickerDialog() },
            pickImageFromGallery = { galleryImageLauncher.launch("image/*") },
            pickImageFromCamera = { cameraImageLaucher.launch() })
    }

}