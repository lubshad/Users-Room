package com.example.usersroom.ui.screens

sealed class Screens(val route: String) {
    object UserListing: Screens("user-listing-screen")
    object EditDetails: Screens("edit-details-screen")
    object AddUser: Screens("add-user-screen")
}
