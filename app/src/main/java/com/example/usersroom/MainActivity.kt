package com.example.usersroom

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.usersroom.ui.screens.Navigation
import com.example.usersroom.ui.theme.UsersRoomTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UsersRoomTheme {
                Navigation()
            }
        }
    }
}
