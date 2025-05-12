package com.suresh.latestandroidstructure.features.profile.data.repository

import com.suresh.latestandroidstructure.core.data.local.room.UserDao
import com.suresh.latestandroidstructure.features.auth.data.dto.UserDetailDto
import com.suresh.latestandroidstructure.features.auth.data.mapper.toDomain
import com.suresh.latestandroidstructure.features.auth.data.mapper.toEntity
import com.suresh.latestandroidstructure.features.auth.domain.model.UserDetailDomain
import com.suresh.latestandroidstructure.features.profile.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
) : UserRepository {
    override suspend fun getUser(userId: Int): UserDetailDomain {
        return userDao.getUserById(userId).toDomain()
    }

    override suspend fun updateUser(user: UserDetailDto): Int {
        return userDao.updateUser(user.toEntity())
    }
}