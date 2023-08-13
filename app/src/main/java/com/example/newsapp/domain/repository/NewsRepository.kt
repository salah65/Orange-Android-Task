package com.example.newsapp.domain.repository

import com.example.newsapp.data.localDb.entities.ArticleEntity
import com.example.newsapp.data.network.ResponseWrapper
import com.example.newsapp.data.network.response.GetAllNewsResponse
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun fetchNews(
        query: String,
        sortBy: String,
        searchIn: String,
        pageNumber: Int,
        pageSize: Int
    ): ResponseWrapper<GetAllNewsResponse>

    suspend fun cacheNews(news: List<ArticleEntity>)

    suspend fun getNews(query: String): List<ArticleEntity>
}