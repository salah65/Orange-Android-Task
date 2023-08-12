package com.example.newsapp.domain.repository

import com.example.newsapp.data.network.ResponseWrapper
import com.example.newsapp.data.network.response.GetAllNewsResponse
import com.example.newsapp.domain.core.Sort

interface NewsRepository {
    suspend fun fetchNews(query: String, sortBy: Sort): ResponseWrapper<GetAllNewsResponse>
}