package com.example.newsapp.domain.useCases

import com.example.newsapp.data.network.ResponseWrapper
import com.example.newsapp.domain.core.Sort
import com.example.newsapp.domain.mapper.mapToListOfNews
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class GetAllNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(
        query: String, sort: Sort = Sort.PUBLISHED_AT, pageNumber: Int = 1, pageSize: Int = 10
    ): ResponseWrapper<List<Article>> {
        return when (val result = newsRepository.fetchNews(query, sort, pageNumber, pageSize)) {
            is ResponseWrapper.Error -> result
            is ResponseWrapper.Success -> ResponseWrapper.Success(result.data.articles.mapToListOfNews())
        }
    }
}