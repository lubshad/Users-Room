package com.example.usersroom.data.room.user

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDataBase : RoomDatabase() {
    abstract fun getUserDao(): UserDao

    companion object {

        @Volatile
        var INSTANCE: UserDataBase? = null

        fun getDataBase(context: Context): UserDataBase {
            if (INSTANCE == null) {
                synchronized(UserDataBase) {
                    INSTANCE =
                        Room.databaseBuilder(context, UserDataBase::class.java, "user_database")
                            .build()
                }
            }
            return INSTANCE!!
        }
    }
}