package com.suresh.latestandroidstructure.features.home.data.remote.model

data class NewsResponseDto(
    val articles: List<Article>? = null,
    val status: String? = null,
    val totalResults: Int = -1
)