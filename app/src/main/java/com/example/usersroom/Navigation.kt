package com.example.usersroom

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
//import com.google.accompanist.navigation.animation.AnimatedNavHost
//import com.google.accompanist.navigation.animation.composable

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.UserListing.route) {
        composable(Screens.UserListing.route) {
            UserListingScreen(navController)
        }
        composable(Screens.AddUser.route) {
            AddUserScreen()
        }
        composable(Screens.EditDetails.route) {
            EditUserDetails()
        }
//        composable(
//            "Blue",
//            enterTransition = { initial, _ ->
//                when (initial.destination.route) {
//                    "Red" ->
//                        slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(700))
//                    else -> null
//                }
//            },
//            exitTransition = { _, target ->
//                when (target.destination.route) {
//                    "Red" ->
//                        slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(700))
//                    else -> null
//                }
//            },
//            popEnterTransition = { initial, _ ->
//                when (initial.destination.route) {
//                    "Red" ->
//                        slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(700))
//                    else -> null
//                }
//            },
//            popExitTransition = { _, target ->
//                when (target.destination.route) {
//                    "Red" ->
//                        slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(700))
//                    else -> null
//                }
//            }
//        ) { BlueScreen(navController) }
    }
}