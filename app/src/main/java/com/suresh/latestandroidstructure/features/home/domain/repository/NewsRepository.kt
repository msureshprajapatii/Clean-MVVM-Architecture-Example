package com.suresh.latestandroidstructure.features.home.domain.repository

import com.suresh.latestandroidstructure.features.home.data.remote.model.NewsResponseDto
import com.suresh.latestandroidstructure.features.home.domain.model.NewsResponseDomain
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNews(queryMap: Map<String, String>): NewsResponseDto
}