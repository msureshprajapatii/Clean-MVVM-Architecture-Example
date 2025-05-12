package com.suresh.latestandroidstructure.features.splash.domain.usecase

import com.suresh.latestandroidstructure.core.data.local.preferences.UserPreferences
import javax.inject.Inject

class GetLoginStatusUseCase @Inject constructor(
    private val userPreferences: UserPreferences
) {
    operator fun invoke(): Boolean {
        return userPreferences.isLoggedIn()
    }
}