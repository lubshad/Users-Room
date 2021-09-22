package com.example.usersroom.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.usersroom.EditUserDetails
import com.example.usersroom.ui.screens.add_user_screen.AddUserScreen
import com.example.usersroom.ui.screens.list_user_screen.UserListingScreen
import dagger.hilt.android.qualifiers.ApplicationContext

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.UserListing.route) {
        composable(Screens.UserListing.route) {
            UserListingScreen(navController)
        }
        composable(Screens.AddUser.route) {
            AddUserScreen(navController = navController)
        }
        composable(Screens.EditDetails.route) {
            EditUserDetails(navController)
        }
    }
}