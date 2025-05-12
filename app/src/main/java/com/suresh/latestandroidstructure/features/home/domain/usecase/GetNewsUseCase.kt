package com.suresh.latestandroidstructure.features.home.domain.usecase

import com.suresh.latestandroidstructure.features.home.data.remote.mapper.toDomain
import com.suresh.latestandroidstructure.features.home.data.remote.model.NewsResponseDto
import com.suresh.latestandroidstructure.features.home.domain.model.NewsResponseDomain
import com.suresh.latestandroidstructure.features.home.domain.repository.NewsRepository
import com.suresh.latestandroidstructure.core.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(
        queryMap: Map<String, String>
    ): Flow<Resource<NewsResponseDomain>> = flow {
        try {
            emit(Resource.Loading)

            val response: NewsResponseDto = repository.getNews(queryMap)
            val domainModel: NewsResponseDomain = response.toDomain()
            emit(Resource.Success(data = domainModel))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "UseCase Error"))
        }
    }
}
