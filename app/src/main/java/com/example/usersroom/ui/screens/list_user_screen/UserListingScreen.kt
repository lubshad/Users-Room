package com.example.usersroom.ui.screens.list_user_screen

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.usersroom.ui.screens.Screens

@Composable
fun UserListingScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Users List") })

        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screens.AddUser.route)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add User")
            }
        }
    ) {

    }
}
