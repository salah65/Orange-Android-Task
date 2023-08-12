package com.example.newsapp.data.repositoryImplementation

import com.example.newsapp.data.network.Endpoints
import com.example.newsapp.data.network.ResponseWrapper
import com.example.newsapp.data.network.apiCallHandler
import com.example.newsapp.data.network.response.GetAllNewsResponse
import com.example.newsapp.domain.core.Sort
import com.example.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(private val endpoints: Endpoints) : NewsRepository {
    override suspend fun fetchNews(query: String, sortBy: Sort): ResponseWrapper<GetAllNewsResponse> {
       return apiCallHandler {
           endpoints.getNewsList(query, sortBy.by)
       }
    }
}