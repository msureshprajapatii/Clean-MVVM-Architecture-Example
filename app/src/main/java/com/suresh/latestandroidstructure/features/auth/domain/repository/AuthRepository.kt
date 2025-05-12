package com.suresh.latestandroidstructure.features.auth.domain.repository

import com.suresh.latestandroidstructure.features.auth.data.dto.UserDetailDto
import com.suresh.latestandroidstructure.features.auth.domain.model.UserDetailDomain

interface AuthRepository {
    suspend fun insertUser(user: UserDetailDto): Long
    suspend fun loginUser(email:String,password:String): UserDetailDomain?
}