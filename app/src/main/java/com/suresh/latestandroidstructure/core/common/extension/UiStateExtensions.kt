package com.suresh.latestandroidstructure.core.common.extension

import com.suresh.latestandroidstructure.core.state.Event
import com.suresh.latestandroidstructure.core.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow

// Shows or hides loader
fun <T> MutableStateFlow<Event<UiState<T>>?>.emitLoading(show: Boolean) {
    value = Event(UiState.Loading(show))
}

// Emits an error state
fun <T> MutableStateFlow<Event<UiState<T>>?>.emitError(msg: String) {
    value = Event(UiState.Error(msg))
}

// Emits a success state
fun <T> MutableStateFlow<Event<UiState<T>>?>.emitSuccess(data: T) {
    value = Event(UiState.Success(data))
}