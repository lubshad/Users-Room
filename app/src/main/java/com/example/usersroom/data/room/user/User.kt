package com.example.usersroom.data.room.user

import android.graphics.Bitmap
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    var firstName: String,
    var lastName: String,
    var age: Int,
    val userImage: Bitmap,
    @Embedded
    val address: Address,
)
