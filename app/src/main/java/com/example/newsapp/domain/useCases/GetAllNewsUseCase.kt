package com.example.newsapp.domain.useCases

import com.example.newsapp.data.network.ResponseWrapper
import com.example.newsapp.domain.core.SearchIn
import com.example.newsapp.domain.core.SortBy
import com.example.newsapp.domain.mapper.mapToListOfNews
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class GetAllNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(
        query: String,
        sort: SortBy = SortBy.PUBLISHED_AT,
        searchIn: SearchIn = SearchIn.TITLE,
        pageNumber: Int = 1,
        pageSize: Int = 10
    ): ResponseWrapper<List<Article>> {
        return when (val result =
            newsRepository.fetchNews(query, sort.value, searchIn.value, pageNumber, pageSize)) {
            is ResponseWrapper.Error -> result
            is ResponseWrapper.Success -> ResponseWrapper.Success(result.data.articles.mapToListOfNews())
        }
    }
}