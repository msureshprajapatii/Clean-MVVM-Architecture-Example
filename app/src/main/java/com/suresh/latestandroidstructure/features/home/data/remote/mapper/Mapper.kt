package com.suresh.latestandroidstructure.features.home.data.remote.mapper

import com.suresh.latestandroidstructure.features.home.data.remote.model.Article
import com.suresh.latestandroidstructure.features.home.data.remote.model.NewsResponseDto
import com.suresh.latestandroidstructure.features.home.data.remote.model.Source
import com.suresh.latestandroidstructure.features.home.domain.model.ArticleDomain
import com.suresh.latestandroidstructure.features.home.domain.model.NewsResponseDomain
import com.suresh.latestandroidstructure.features.home.domain.model.SourceDomain

fun NewsResponseDto.toDomain(): NewsResponseDomain {
    return NewsResponseDomain(
        articles = this.articles?.map { it.toDomain() } ?: emptyList(),
        status = this.status.orEmpty(),
        totalResults = this.totalResults
    )
}

private fun Article.toDomain(): ArticleDomain {
    return ArticleDomain(
        author = this.author.orEmpty(),
        content = this.content.orEmpty(),
        description = this.description.orEmpty(),
        publishedAt = this.publishedAt.orEmpty(),
        source = this.source.toDomain(),
        title = this.title.orEmpty(),
        url = this.url.orEmpty(),
        urlToImage = this.urlToImage.orEmpty()
    )
}

private fun Source.toDomain(): SourceDomain {
    return SourceDomain(
        id = this.id.orEmpty(),
        name = this.name.orEmpty()
    )
}