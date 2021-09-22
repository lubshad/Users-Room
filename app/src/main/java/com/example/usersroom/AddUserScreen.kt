package com.example.usersroom

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun AddUserScreen(){
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Add New User") })

        }
    ) {

    }
}