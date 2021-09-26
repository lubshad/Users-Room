package com.example.usersroom.ui.screens.add_user_screen

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.usersroom.DefaultSpacer
import com.example.usersroom.ui.screens.Screens
import dagger.hilt.android.qualifiers.ApplicationContext

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
                    if (age!= null) {
                        addUserViewModel.age.value = age.toString()
                    }
                },
                label = { Text("Age") },
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