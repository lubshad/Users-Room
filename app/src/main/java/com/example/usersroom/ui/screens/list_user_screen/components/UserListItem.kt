package com.example.usersroom.ui.screens.list_user_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.usersroom.DefaultSpacer
import com.example.usersroom.DefaultSpacerHorizontal
import com.example.usersroom.data.room.user.User

@Composable
fun UserListItem(user: User, onClick:()-> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable {
                onClick()
            }
            .fillMaxWidth()
    ) {
        DefaultSpacerHorizontal()
        Text(text = user.id.toString(),
            style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold))
        DefaultSpacerHorizontal()
        Text(text = "${user.firstName} ${user.lastName}   ${user.age}",
            style = MaterialTheme.typography.h6)
    }
}