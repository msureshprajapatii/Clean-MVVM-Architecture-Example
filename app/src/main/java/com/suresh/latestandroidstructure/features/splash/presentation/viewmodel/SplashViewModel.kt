package com.suresh.latestandroidstructure.features.splash.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suresh.latestandroidstructure.features.splash.domain.usecase.GetLoginStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getLoginStatusUseCase: GetLoginStatusUseCase
) : ViewModel() {

    private val _navigateTo = MutableStateFlow<Destination?>(null)
    val navigateTo: StateFlow<Destination?> = _navigateTo

    init {
        checkLoginStatus()
    }

    private fun checkLoginStatus() {
        viewModelScope.launch {
            delay(2000)
            val isLoggedIn = getLoginStatusUseCase()
            _navigateTo.value = if (isLoggedIn) Destination.Home else Destination.Login
        }
    }

    sealed class Destination {
        object Home : Destination()
        object Login : Destination()
    }
}