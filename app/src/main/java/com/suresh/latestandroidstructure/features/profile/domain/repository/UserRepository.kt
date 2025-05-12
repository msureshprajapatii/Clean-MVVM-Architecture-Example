package com.suresh.latestandroidstructure.features.profile.domain.repository

import com.suresh.latestandroidstructure.features.auth.data.dto.UserDetailDto
import com.suresh.latestandroidstructure.features.auth.domain.model.UserDetailDomain

interface UserRepository {
    suspend fun getUser(userId: Int): UserDetailDomain?
    suspend fun updateUser(user: UserDetailDto): Int
}
