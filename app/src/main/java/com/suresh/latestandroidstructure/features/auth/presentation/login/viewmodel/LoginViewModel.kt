package com.suresh.latestandroidstructure.features.auth.presentation.login.viewmodel

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
import com.suresh.latestandroidstructure.features.auth.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val userPreferences: UserPreferences
) : ViewModel() {

    val email = MutableStateFlow("")
    val password = MutableStateFlow("")

    private val _uiState = MutableStateFlow<Event<UiState<String>>?>(null)
    val uiState: StateFlow<Event<UiState<String>>?> = _uiState

    val isLoading = MutableStateFlow(false)

    private val _isPasswordVisible = MutableStateFlow(false)
    val isPasswordVisible: StateFlow<Boolean> = _isPasswordVisible

    fun validateAndLoginUser() {
        val email = email.value
        val password = password.value

        if (!InputValidator.isFieldNotEmpty(email,password)) {
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

        isLoading.value = true
        loadUserDetails(email, password)
    }

    private fun loadUserDetails(email: String, password: String) {
        val hashedPassword = PasswordUtils.hashPassword(password)

        viewModelScope.launch(Dispatchers.IO) {
            val userDetailDomain = loginUseCase.invoke(email,hashedPassword)

            if (userDetailDomain != null) {
                userPreferences.setUserData(
                    UserData(
                        userId = userDetailDomain.id.toLong(),
                        isLoggedIn = true
                    )
                )
                _uiState.emitSuccess("Hi ${userDetailDomain.username}, you're logged in!")
            } else {
                _uiState.emitError("Authentication failed. Please check your email and password.")
            }

            isLoading.value = false
        }
    }

    fun togglePasswordVisibility() {
        _isPasswordVisible.value = !_isPasswordVisible.value
    }
}