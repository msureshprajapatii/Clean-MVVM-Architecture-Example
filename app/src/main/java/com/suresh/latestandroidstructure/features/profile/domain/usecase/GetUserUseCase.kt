package com.suresh.latestandroidstructure.features.profile.domain.usecase

import com.suresh.latestandroidstructure.features.auth.domain.model.UserDetailDomain
import com.suresh.latestandroidstructure.features.profile.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(userId: Int): UserDetailDomain? {
        return repository.getUser(userId)
    }
}