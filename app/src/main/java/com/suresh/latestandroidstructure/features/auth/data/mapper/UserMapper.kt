package com.suresh.latestandroidstructure.features.auth.data.mapper

import com.suresh.latestandroidstructure.core.data.local.room.UserEntity
import com.suresh.latestandroidstructure.features.auth.data.dto.UserDetailDto
import com.suresh.latestandroidstructure.features.auth.domain.model.UserDetailDomain

fun UserDetailDomain.toDomainToDto(): UserDetailDto {
    return UserDetailDto(
        id = this.id.toDefault(),
        username = this.username,
        email = this.email,
        phoneNumber = this.phoneNumber,
        bio = this.bio,
        password = this.password.orEmpty()
    )
}

fun UserDetailDto.toDomain(): UserDetailDomain {
    return UserDetailDomain(
        id = this.id.toDefault(),
        username = this.username.orEmpty(),
        email = this.email.orEmpty(),
        phoneNumber = this.phoneNumber.orEmpty(),
        bio = this.bio.orEmpty(),
        password = this.password.orEmpty()
    )
}

private fun Int?.toDefault(): Int {
    return this ?: 0
}

fun UserEntity.toDomain() = UserDetailDomain(
    id = id,
    username = username,
    email = email,
    phoneNumber = phoneNumber,
    bio = bio,
    password = password
)

fun UserDetailDto.toEntity(): UserEntity {
    return UserEntity(
        id = this.id.toDefault(),
        username = this.username.orEmpty(),
        email = this.email.orEmpty(),
        password = this.password.orEmpty(),
        phoneNumber = this.phoneNumber.orEmpty(),
        bio = this.bio.orEmpty()
    )
}