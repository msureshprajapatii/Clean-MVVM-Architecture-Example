package com.suresh.latestandroidstructure.core.di

import android.content.Context
import com.suresh.latestandroidstructure.core.data.local.preferences.UserPreferences
import com.suresh.latestandroidstructure.core.data.local.room.UserDao
import com.suresh.latestandroidstructure.features.auth.data.repository.AuthRepositoryImpl
import com.suresh.latestandroidstructure.features.auth.domain.repository.AuthRepository
import com.suresh.latestandroidstructure.features.profile.data.repository.UserRepositoryImpl
import com.suresh.latestandroidstructure.features.profile.domain.repository.UserRepository
import com.suresh.latestandroidstructure.features.profile.domain.usecase.GetUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideUserPreferences(@ApplicationContext context: Context): UserPreferences {
        return UserPreferences(context)
    }

    @Provides
    fun provideGetUserRepository(userDao: UserDao): UserRepository =
        UserRepositoryImpl(userDao)

    @Provides
    fun provideGetUserCase(repository: UserRepository): GetUserUseCase {
        return GetUserUseCase(repository)
    }
}