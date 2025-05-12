package com.suresh.latestandroidstructure.features.auth.domain.model

data class UserDetailDomain(
    val id: Int = 0,
    val username: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val bio: String = "",
    val password: String = ""
)