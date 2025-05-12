package com.suresh.latestandroidstructure.features.home.presentation.ui.model

import com.suresh.latestandroidstructure.features.home.domain.model.NewsResponseDomain

sealed class NewsUiState {
    data object None : NewsUiState()
    data object Loading : NewsUiState()
    data class Success(val data: NewsResponseDomain) : NewsUiState()
    data class Error(val message: String) : NewsUiState()
}