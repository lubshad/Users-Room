package com.example.usersroom

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.usersroom.common.defaultPadding

@Composable
fun DefaultSpacer() {
    Spacer(modifier = Modifier.height(defaultPadding / 2))
}


@Composable
fun DefaultSpacerHorizontal() {
    Spacer(modifier = Modifier.width(defaultPadding))
}