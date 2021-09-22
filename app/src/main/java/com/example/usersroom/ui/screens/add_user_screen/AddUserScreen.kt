package com.example.usersroom.ui.screens.add_user_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.usersroom.DefaultSpacer

@Composable
fun AddUserScreen(addUserViewModel: AddUserViewModel= viewModel())  {
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
                    addUserViewModel.age.value = it
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