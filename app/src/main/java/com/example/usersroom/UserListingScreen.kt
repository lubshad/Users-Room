package com.example.usersroom

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun UserListingScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Users List") })

        }
    ) {

    }
}
