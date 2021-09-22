package com.example.usersroom.ui.screens.list_user_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.usersroom.ui.screens.Screens

@Composable
fun UserListingScreen(
    navController: NavHostController,
    userListViewModel: UserListViewModel = hiltViewModel(),
) {
    val userList = userListViewModel.userList.observeAsState()
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
        if (!userList.value.isNullOrEmpty()) {
            LazyColumn {
                items(userList.value!!) { user ->
                    Row(horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = user.id.toString(), style = MaterialTheme.typography.h2)
                        Text(text = user.firstName, style = MaterialTheme.typography.h6)
                        Text(text = user.lastName, style = MaterialTheme.typography.h6)
                        Text(text = user.age.toString(), style = MaterialTheme.typography.h6)
                    }

                }
            }
        }

    }
}
