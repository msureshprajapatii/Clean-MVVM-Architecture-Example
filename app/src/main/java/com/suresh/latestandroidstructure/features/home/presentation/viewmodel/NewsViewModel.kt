package com.suresh.latestandroidstructure.features.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suresh.latestandroidstructure.core.util.Resource
import com.suresh.latestandroidstructure.features.home.domain.usecase.GetNewsUseCase
import com.suresh.latestandroidstructure.features.home.presentation.ui.model.NewsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {

    private val _newsUiState = MutableStateFlow<NewsUiState>(NewsUiState.None)
    val newsUiState = _newsUiState.asStateFlow()

    init {
        fetchNews()
    }

    private fun fetchNews(
        country: String = "us",
        category: String = "business",
        apiKey: String = "5806374e893449769b989a78c8a9a195"
    ) {
        val queryMap = mapOf(
            "country" to country,
            "category" to category,
            "apiKey" to apiKey
        )

        viewModelScope.launch {
            getNewsUseCase(queryMap)
                .collect { result ->
                    _newsUiState.value = when (result) {
                        is Resource.Loading -> NewsUiState.Loading
                        is Resource.Success -> NewsUiState.Success(result.data)
                        is Resource.Error -> NewsUiState.Error(result.message)
                    }
                }
        }
    }
}