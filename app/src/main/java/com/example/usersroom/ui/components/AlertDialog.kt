package com.example.usersroom.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ShowAlertDialog() {

    AlertDialog(
        onDismissRequest = {
                           Log.i("Alert", "CANCELLED")
        },
        confirmButton = {
                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "Delete User")
                        }
        },
        dismissButton = {
                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "Cancel")
                        }
        },
        text = { Text(text = "Are you sure delete this user") }
    )
}