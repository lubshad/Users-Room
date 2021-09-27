package com.example.usersroom.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.usersroom.ui.screens.edit_details_screen.EditUserDetails
import com.example.usersroom.data.room.user.User
import com.example.usersroom.ui.screens.add_user_screen.AddUserScreen
import com.example.usersroom.ui.screens.list_user_screen.UserListingScreen
import com.google.gson.Gson

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
        composable(Screens.EditDetails.route + "?user={user}",
        arguments = listOf(navArgument("user"){})
            ) {
            it.arguments?.getString("user").let {
                userString->
                val user = Gson().fromJson(userString, User::class.java)
                EditUserDetails(navController)
            }
        }
    }
}