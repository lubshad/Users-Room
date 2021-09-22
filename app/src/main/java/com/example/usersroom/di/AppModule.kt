package com.example.usersroom.di

import android.content.Context
import com.example.usersroom.data.respository.UserRepositoryImpl
import com.example.usersroom.data.room.user.UserDao
import com.example.usersroom.data.room.user.UserDataBase
import com.example.usersroom.domain.repository.UserRepository
import com.example.usersroom.domain.usecase.AddUserUsecase
import com.example.usersroom.domain.usecase.GetAllUserUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppContext(@ApplicationContext context:Context):Context {
        return context
    }

    @Provides
    @Singleton
    fun provideUserDatabase(context:Context) :UserDataBase{
        return UserDataBase.getDataBase(context)
    }

    @Provides
    @Singleton
    fun provideUserDao(userDataBase: UserDataBase) : UserDao {
        return userDataBase.getUserDao()
    }

    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao) :UserRepository {
        return UserRepositoryImpl(userDao)
    }

    @Provides
    @Singleton
    fun provideAddUserUsecase(userRepository: UserRepository) :AddUserUsecase {
        return AddUserUsecase(userRepository)
    }

    @Provides
    @Singleton
    fun provideGetAllUserUsecase(userRepository: UserRepository) :GetAllUserUsecase {
        return GetAllUserUsecase(userRepository)
    }
}