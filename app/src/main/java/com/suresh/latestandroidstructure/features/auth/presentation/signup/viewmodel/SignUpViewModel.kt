package com.suresh.latestandroidstructure.features.auth.presentation.signup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suresh.latestandroidstructure.core.common.extension.emitError
import com.suresh.latestandroidstructure.core.common.extension.emitSuccess
import com.suresh.latestandroidstructure.core.data.local.preferences.UserData
import com.suresh.latestandroidstructure.core.data.local.preferences.UserPreferences
import com.suresh.latestandroidstructure.core.state.Event
import com.suresh.latestandroidstructure.core.state.UiState
import com.suresh.latestandroidstructure.core.util.PasswordUtils
import com.suresh.latestandroidstructure.core.validation.InputValidator
import com.suresh.latestandroidstructure.features.auth.data.dto.UserDetailDto
import com.suresh.latestandroidstructure.features.auth.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val userPreferences: UserPreferences
) : ViewModel() {

    val userName = MutableStateFlow("")
    val email = MutableStateFlow("")
    val password = MutableStateFlow("")
    val confirmPassword = MutableStateFlow("")

    private val _uiState = MutableStateFlow<Event<UiState<String>>?>(null)
    val uiState: StateFlow<Event<UiState<String>>?> = _uiState

    val isLoading = MutableStateFlow(false)

    fun validateAndRegisterUser() {
        val userName = userName.value
        val email = email.value
        val password = password.value
        val confirmPassword = confirmPassword.value

        if (!InputValidator.isFieldNotEmpty(userName, email, password, confirmPassword)) {
            _uiState.emitError("All fields are required.")
            return
        }

        if (!InputValidator.isEmailValid(email)) {
            _uiState.emitError("Invalid email format.")
            return
        }

        if (!InputValidator.isPasswordValid(password)) {
            _uiState.emitError("Password must be at least 6 characters.")
            return
        }

        if (!InputValidator.doPasswordsMatch(password, confirmPassword)) {
            _uiState.emitError("Passwords do not match.")
            return
        }

        isLoading.value = true
        saveUserToDb(userName, email, password)
    }

    private fun saveUserToDb(userName: String, email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val hashedPassword = PasswordUtils.hashPassword(password)

            val user = UserDetailDto(username = userName, email = email, password = hashedPassword)
            val result = authUseCase(user)

            if (result > 0) {
                userPreferences.setUserData(
                    UserData(
                        userId = result,
                        isLoggedIn = true
                    )
                )
                _uiState.emitSuccess("Registration Successful")
            } else {
                _uiState.emitError("Failed to save user")
            }

            isLoading.value = false
        }
    }
}