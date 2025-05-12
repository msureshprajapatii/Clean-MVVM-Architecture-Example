package com.suresh.latestandroidstructure.features.profile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suresh.latestandroidstructure.core.common.extension.emitError
import com.suresh.latestandroidstructure.core.common.extension.emitSuccess
import com.suresh.latestandroidstructure.core.data.local.preferences.UserPreferences
import com.suresh.latestandroidstructure.core.state.Event
import com.suresh.latestandroidstructure.core.state.UiState
import com.suresh.latestandroidstructure.features.auth.data.dto.UserDetailDto
import com.suresh.latestandroidstructure.features.auth.domain.model.UserDetailDomain
import com.suresh.latestandroidstructure.features.profile.domain.usecase.GetUserUseCase
import com.suresh.latestandroidstructure.features.profile.domain.usecase.UpdateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val userPreferences: UserPreferences
) : ViewModel() {

    val userName = MutableStateFlow("")
    val email = MutableStateFlow("")
    val phoneNumber = MutableStateFlow("")
    val bio = MutableStateFlow("")
    val isLoading = MutableStateFlow(false)

    private val _uiState = MutableStateFlow<Event<UiState<String>>?>(null)
    val uiState: StateFlow<Event<UiState<String>>?> = _uiState

    init {
        viewModelScope.launch {
            val result: UserDetailDomain? =
                getUserUseCase.invoke(userPreferences.getLoggedInUserId())

            userName.value = result?.username.orEmpty()
            email.value = result?.email.orEmpty()
            phoneNumber.value = result?.phoneNumber.orEmpty()
            bio.value = result?.bio.orEmpty()
        }
    }

    fun updateProfile() {
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
           val result = updateUserUseCase.invoke(
                UserDetailDto(
                    id = userPreferences.getLoggedInUserId(),
                    username = userName.value,
                    email = email.value,
                    phoneNumber = phoneNumber.value,
                    bio = bio.value,
                )
            )
            if (result > 0){
                _uiState.emitSuccess("Profile updated Successful")
            }
            else {
                _uiState.emitError("Failed to update user")
            }
            isLoading.value = false
        }
    }

    fun logout() {
        viewModelScope.launch(Dispatchers.Default) {
            userPreferences.logout()
        }
    }
}