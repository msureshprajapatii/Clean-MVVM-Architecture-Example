package com.suresh.latestandroidstructure.features.auth.domain.usecase

import com.suresh.latestandroidstructure.features.auth.domain.model.UserDetailDomain
import com.suresh.latestandroidstructure.features.auth.domain.repository.AuthRepository

class LoginUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(email:String,password:String): UserDetailDomain? {
        return repository.loginUser(email,password)
    }
}