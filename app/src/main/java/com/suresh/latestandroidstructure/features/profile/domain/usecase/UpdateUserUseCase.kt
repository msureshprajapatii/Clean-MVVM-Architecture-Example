package com.suresh.latestandroidstructure.features.profile.domain.usecase

import com.suresh.latestandroidstructure.features.auth.data.dto.UserDetailDto
import com.suresh.latestandroidstructure.features.auth.data.mapper.toDomainToDto
import com.suresh.latestandroidstructure.features.auth.domain.model.UserDetailDomain
import com.suresh.latestandroidstructure.features.profile.domain.repository.UserRepository
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: UserDetailDto): Int {
        val existingUser: UserDetailDomain? = repository.getUser(user.id ?: 0)

        val updatedUser = existingUser?.copy(
            username = user.username.orEmpty(),
            email = user.email.orEmpty(),
            phoneNumber = user.phoneNumber.orEmpty(),
            bio = user.bio.orEmpty(),
        )?.toDomainToDto() ?: UserDetailDto()

        return repository.updateUser(updatedUser)
    }
}