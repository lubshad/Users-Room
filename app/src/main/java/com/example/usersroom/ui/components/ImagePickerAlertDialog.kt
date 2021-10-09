package com.example.usersroom.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.usersroom.DefaultSpacer
import com.example.usersroom.DefaultSpacerHorizontal

@Composable
fun ShowImagePickerAlertDialog(
    dismissDialog: () -> Unit,
    pickImageFromGallery: () -> Unit,
    pickImageFromCamera: () -> Unit,
) {
    Dialog(
        onDismissRequest = { dismissDialog() }, properties = DialogProperties()
    ) {
        Box(
            Modifier
                .background(Color.White)
                .padding(10.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Text(text = "Pick Image From", style = MaterialTheme.typography.h6)
                DefaultSpacer()
                DefaultSpacer()
                DefaultSpacer()
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                ) {
                    Column() {

                        IconButton(onClick = pickImageFromCamera) {
                            Icon(imageVector = Icons.Rounded.Face, contentDescription = "Camera")
                        }
                        Text(text = "Camera")
                    }
                    DefaultSpacerHorizontal()
                    Column() {

                        IconButton(onClick = pickImageFromGallery) {
                            Icon(imageVector = Icons.Filled.Settings,
                                contentDescription = "Gallery")
                        }
                        Text("Gallery")
                    }
                }
            }

        }
    }
}