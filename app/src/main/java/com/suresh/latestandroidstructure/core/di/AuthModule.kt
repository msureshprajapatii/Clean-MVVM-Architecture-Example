package com.suresh.latestandroidstructure.core.di

import com.suresh.latestandroidstructure.core.data.local.room.UserDao
import com.suresh.latestandroidstructure.features.auth.data.repository.AuthRepositoryImpl
import com.suresh.latestandroidstructure.features.auth.domain.repository.AuthRepository
import com.suresh.latestandroidstructure.features.auth.domain.usecase.AuthUseCase
import com.suresh.latestandroidstructure.features.auth.domain.usecase.LoginUseCase
import com.suresh.latestandroidstructure.features.profile.domain.repository.UserRepository
import com.suresh.latestandroidstructure.features.profile.domain.usecase.GetUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    fun provideAuthRepository(userDao: UserDao): AuthRepository =
        AuthRepositoryImpl(userDao)

    @Provides
    fun provideRegisterUserUseCase(repository: AuthRepository): AuthUseCase =
        AuthUseCase(repository)

    @Provides
    fun provideLoginUseCase(repository: AuthRepository): LoginUseCase {
        return LoginUseCase(repository)
    }

}
