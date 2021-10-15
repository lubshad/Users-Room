package com.example.usersroom.ui.screens.add_user_screen

import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.navigation.NavController
import com.example.usersroom.DefaultSpacer
import com.example.usersroom.ui.components.ShowImagePickerAlertDialog


@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun AddUserScreen(
    addUserViewModel: AddUserViewModel = hiltViewModel(),
    navController: NavController,
) {


    val scrollableState = rememberScrollState()




    val cameraImageLaucher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicturePreview()) {
            if (it != null) {
                addUserViewModel.setImageFromCamera(it)
            }
        }

    val galleryImageLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
            if (it != null) {
                addUserViewModel.setImageFromGallery(it)
            }
        }




    addUserViewModel.navController = navController

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Add New User") })
        }
    ) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .scrollable(scrollableState, orientation = Orientation.Vertical),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            item {
                DefaultSpacer()
            }
            item {

                Box() {
                    addUserViewModel.image.value?.let { image ->
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
                        if (!addUserViewModel.showDialog.value) {
                            addUserViewModel.showImagePickerDialog()
                        }
                    },
                        modifier = Modifier.align(Alignment.BottomCenter)) {
                        Icon(imageVector = if (addUserViewModel.image.value == null) Icons.Default.Add else Icons.Default.Edit,
                            contentDescription = "Edit Photo",
                            tint = if (addUserViewModel.image.value == null) Color.Black else Color.White)
                    }
                }
            }
            item {

                DefaultSpacer()
            }
            item {

                OutlinedTextField(
                    value = addUserViewModel.firstName.value, onValueChange = {
                        addUserViewModel.firstName.value = it
                    }, label = { Text("First Name") })
            }
            item {

                DefaultSpacer()
            }
            item {

                OutlinedTextField(value = addUserViewModel.lastName.value, onValueChange = {
                    addUserViewModel.lastName.value = it
                }, label = { Text("Last Name") })
            }
            item {

                DefaultSpacer()
            }
            item {

                OutlinedTextField(value = addUserViewModel.age.value,
                    onValueChange = {
                        val age = it.toIntOrNull()
                        if (age != null) {
                            addUserViewModel.age.value = age.toString()
                        }
                    },
                    label = { Text("Age") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
            }
            item {

                DefaultSpacer()
            }
            item {

                OutlinedTextField(value = addUserViewModel.streetName.value, onValueChange = {
                    addUserViewModel.streetName.value = it
                }, label = { Text("Street Name") })
            }
            item {

                DefaultSpacer()
            }
            item {

                OutlinedTextField(value = addUserViewModel.streetNumber.value,
                    onValueChange = {
                        val streetNumber = it.toIntOrNull()
                        if (streetNumber != null) {
                            addUserViewModel.streetNumber.value = streetNumber.toString()
                        }
                    },
                    label = { Text("Street Number") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
            }
            item {
                DefaultSpacer()
                DefaultSpacer()
            }
            item {

                Button(onClick = {
                    addUserViewModel.addUser()

                }) {
                    Text(text = "Add New User")
                }
            }
        }
    }
    if (addUserViewModel.showDialog.value) {
        ShowImagePickerAlertDialog(dismissDialog = { addUserViewModel.hideImagePickerDialog() },
            pickImageFromGallery = { galleryImageLauncher.launch("image/*") },
            pickImageFromCamera = { cameraImageLaucher.launch() })
    }
}