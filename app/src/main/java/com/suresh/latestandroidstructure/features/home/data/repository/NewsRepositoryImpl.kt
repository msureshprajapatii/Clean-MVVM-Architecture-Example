package com.suresh.latestandroidstructure.features.home.data.repository

import com.suresh.latestandroidstructure.features.home.data.remote.NewsApiService
import com.suresh.latestandroidstructure.features.home.data.remote.model.NewsResponseDto
import com.suresh.latestandroidstructure.features.home.domain.repository.NewsRepository
import com.suresh.latestandroidstructure.core.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiService: NewsApiService
) : NewsRepository {
    override suspend fun getNews(queryMap: Map<String, String>): NewsResponseDto {
        return apiService.getNews(queryMap)
    }
}