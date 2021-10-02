package com.example.usersroom.ui.screens.add_user_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.usersroom.DefaultSpacer
import com.example.usersroom.R

@Composable
fun AddUserScreen(
    addUserViewModel: AddUserViewModel = hiltViewModel(),
    navController: NavController,
) {

    addUserViewModel.navController = navController

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Add New User") })
        }
    ) {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            DefaultSpacer()

            Box() {
                Image(
                    painter = painterResource(R.drawable.user_image),
                    contentDescription = "avatar",
                    contentScale = ContentScale.Crop,            // crop the image if it's not a square
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)            // add a border (optional)
                )
                IconButton(onClick = { /*TODO*/ }, modifier= Modifier.align(Alignment.BottomCenter)) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Photo", tint = Color.White)
                }
            }
            DefaultSpacer()
            OutlinedTextField(value = addUserViewModel.firstName.value, onValueChange = {
                addUserViewModel.firstName.value = it
            }, label = { Text("First Name") })
            DefaultSpacer()
            OutlinedTextField(value = addUserViewModel.lastName.value, onValueChange = {
                addUserViewModel.lastName.value = it
            }, label = { Text("Last Name") })
            DefaultSpacer()
            OutlinedTextField(value = addUserViewModel.age.value,
                onValueChange = {
                    val age = it.toIntOrNull()
                    if (age != null) {
                        addUserViewModel.age.value = age.toString()
                    }
                },
                label = { Text("Age") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
            DefaultSpacer()
            OutlinedTextField(value = addUserViewModel.streetName.value, onValueChange = {
                addUserViewModel.streetName.value = it
            }, label = { Text("Street Name") })
            DefaultSpacer()
            OutlinedTextField(value = addUserViewModel.streetNumber.value,
                onValueChange = {
                    val streetNumber = it.toIntOrNull()
                    if (streetNumber != null) {
                        addUserViewModel.streetNumber.value = streetNumber.toString()
                    }
                },
                label = { Text("Street Number") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
            DefaultSpacer()
            Button(onClick = {
                addUserViewModel.addUser()

            }) {
                Text(text = "Add New User")
            }
        }
    }
}