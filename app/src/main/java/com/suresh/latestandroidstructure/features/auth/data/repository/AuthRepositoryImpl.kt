package com.suresh.latestandroidstructure.features.auth.data.repository

import com.suresh.latestandroidstructure.core.data.local.room.UserDao
import com.suresh.latestandroidstructure.core.data.local.room.UserEntity
import com.suresh.latestandroidstructure.features.auth.data.dto.UserDetailDto
import com.suresh.latestandroidstructure.features.auth.data.mapper.toDomain
import com.suresh.latestandroidstructure.features.auth.data.mapper.toEntity
import com.suresh.latestandroidstructure.features.auth.domain.model.UserDetailDomain
import com.suresh.latestandroidstructure.features.auth.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val userDao: UserDao
) : AuthRepository {

    override suspend fun insertUser(user: UserDetailDto): Long {
        return userDao.insertUser(user.toEntity())
    }

    override suspend fun loginUser(email: String, password: String): UserDetailDomain? {
        val userDto = userDao.loginUser(email, password)
        return userDto?.toDomain()
    }
}