package com.example.usersroom.data.room.user

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [User::class], version = 3)
@TypeConverters(Converters::class)
abstract class UserDataBase : RoomDatabase() {
    abstract fun getUserDao(): UserDao

    companion object {

        @Volatile
        var INSTANCE: UserDataBase? = null

        fun getDataBase(context: Context): UserDataBase {
            if (INSTANCE == null) {
                synchronized(UserDataBase) {
                    INSTANCE =
                        Room.databaseBuilder(context, UserDataBase::class.java, "user_database_v3")
                            .build()
                }
            }
            return INSTANCE!!
        }
    }
}