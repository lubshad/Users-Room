package com.example.usersroom.ui.components

import android.util.Log
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ShowAlertDialog(dismissDialog: ()->Unit, deleteUser: ()->Unit, message :String) {

    AlertDialog(
        onDismissRequest = {
                           dismissDialog()
        },
        confirmButton = {
                        Button(onClick = {
                            deleteUser()
                        }) {
                            Text(text = "Confirm")
                        }
        },
        dismissButton = {
                        Button(onClick = {
                            dismissDialog()
                        }) {
                            Text(text = "Cancel")
                        }
        },
        text = { Text(text = message) }
    )
}