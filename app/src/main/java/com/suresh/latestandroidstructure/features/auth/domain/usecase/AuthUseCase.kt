package com.suresh.latestandroidstructure.features.auth.domain.usecase

import com.suresh.latestandroidstructure.features.auth.data.dto.UserDetailDto
import com.suresh.latestandroidstructure.features.auth.domain.model.UserDetailDomain
import com.suresh.latestandroidstructure.features.auth.domain.repository.AuthRepository

class AuthUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(user: UserDetailDto): Long {
        return repository.insertUser(user)
    }
}
