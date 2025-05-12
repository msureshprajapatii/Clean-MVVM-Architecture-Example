package com.suresh.latestandroidstructure.core.state

sealed class UiState<out T> {
    data object Idle : UiState<Nothing>()
    data class Loading(val isShowLoader:Boolean) : UiState<Nothing>()
    data class Success<out T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}