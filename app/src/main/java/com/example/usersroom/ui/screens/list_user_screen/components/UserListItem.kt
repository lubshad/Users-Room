package com.example.usersroom.ui.screens.list_user_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.example.usersroom.DefaultSpacer
import com.example.usersroom.DefaultSpacerHorizontal
import com.example.usersroom.data.room.user.User

@Composable
fun UserListItem(user: User, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable {
                onClick()
            }
            .fillMaxWidth()
    ) {
        DefaultSpacerHorizontal()
        Image(bitmap = user.userImage.asImageBitmap(),
            contentDescription = "User Image",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape))
        DefaultSpacerHorizontal()
        Column {
            Text(text = "${user.firstName}",
                style = MaterialTheme.typography.h6)
            DefaultSpacer()
            Text(text = "${user.lastName}",
                style = MaterialTheme.typography.h6)
        }

        DefaultSpacerHorizontal()
        Column {
            Text(text = "${user.address.streetName}",
                style = MaterialTheme.typography.h6)
            DefaultSpacer()
            Text(text = "${user.address.streetNumber}",
                style = MaterialTheme.typography.h6)
        }
    }
}