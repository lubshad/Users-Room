package com.example.usersroom.ui.screens.edit_details_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.usersroom.DefaultSpacer
import com.example.usersroom.data.room.user.User


@Composable
fun EditUserDetails(
    navController: NavHostController,
    editUserViewModel: EditUserViewModel = hiltViewModel(),
) {

    editUserViewModel.navControler = navController

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Edit User Details") })

        }
    ) {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            DefaultSpacer()
            OutlinedTextField(value = editUserViewModel.firstName.value, onValueChange = {
                editUserViewModel.firstName.value = it
            }, label = { Text("First Name") })
            DefaultSpacer()
            OutlinedTextField(value = editUserViewModel.lastName.value, onValueChange = {
                editUserViewModel.lastName.value = it
            }, label = { Text("Last Name") })
            DefaultSpacer()
            OutlinedTextField(value = editUserViewModel.age.value.toString(),
                onValueChange = {
                    editUserViewModel.age.value = it
                },
                label = { Text("Age") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
            DefaultSpacer()
            Button(onClick = {
                editUserViewModel.updateUserDetails()

            }) {
                Text(text = "Update User")
            }
        }
    }
}