package com.example.newsapp.data.repositoryImplementation

import com.example.newsapp.data.localDb.dao.ArticlesDao
import com.example.newsapp.data.localDb.entities.ArticleEntity
import com.example.newsapp.data.network.Endpoints
import com.example.newsapp.data.network.ResponseWrapper
import com.example.newsapp.data.network.apiCallHandler
import com.example.newsapp.data.network.response.GetAllNewsResponse
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(private val endpoints: Endpoints,private val db:ArticlesDao) : NewsRepository {
    override suspend fun fetchNews(
        query: String,
        sortBy: String,
        searchIn: String,
        pageNumber: Int,
        pageSize: Int
    ): ResponseWrapper<GetAllNewsResponse> {
        return apiCallHandler {
            endpoints.getNewsList(query, sortBy, searchIn, pageNumber, pageSize)
        }
    }

    override suspend fun cacheNews(news: List<ArticleEntity>) {
        db.insertItems(news)
    }

    override suspend fun getNews(query: String): List<ArticleEntity> {
      return db.getItems(query = query)
    }
}