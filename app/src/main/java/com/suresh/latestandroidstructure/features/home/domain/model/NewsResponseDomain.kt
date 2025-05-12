package com.suresh.latestandroidstructure.features.home.domain.model

data class NewsResponseDomain(
    val articles: List<ArticleDomain>,
    val status: String,
    val totalResults: Int
)

data class ArticleDomain(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: SourceDomain,
    val title: String,
    val url: String,
    val urlToImage: String
)

data class SourceDomain(
    val id: String,
    val name: String
)