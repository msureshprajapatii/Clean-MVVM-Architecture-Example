package com.suresh.latestandroidstructure.features.home.data.remote

import com.suresh.latestandroidstructure.features.home.data.remote.model.NewsResponseDto
import com.suresh.latestandroidstructure.features.home.domain.model.NewsResponseDomain
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface NewsApiService {

    @GET("top-headlines")
    suspend fun getNews(
        @QueryMap queryMap: Map<String, String>
    ): NewsResponseDto
}
