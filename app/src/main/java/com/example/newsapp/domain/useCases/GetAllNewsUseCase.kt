package com.example.newsapp.domain.useCases

import com.example.newsapp.data.network.ResponseWrapper
import com.example.newsapp.domain.core.SearchIn
import com.example.newsapp.domain.core.SortBy
import com.example.newsapp.domain.mapper.mapToListOfArticles
import com.example.newsapp.domain.mapper.mapToListOfNews
import com.example.newsapp.domain.mapper.mapToListOfNewsEntity
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(
        query: String,
        sort: SortBy = SortBy.PUBLISHED_AT,
        searchIn: SearchIn = SearchIn.TITLE,
        pageNumber: Int = 1,
        pageSize: Int = 10
    ): ResponseWrapper<List<Article>> {
        return withContext(Dispatchers.IO) {
            when (val result =
                newsRepository.fetchNews(query, sort.value, searchIn.value, pageNumber, pageSize)) {
                is ResponseWrapper.Error -> {
                    val news = newsRepository.getNews(query)
                    if (news.isEmpty())
                        result
                    else
                        ResponseWrapper.Success(news.mapToListOfArticles())

                }
                is ResponseWrapper.Success -> {
                    newsRepository.cacheNews(result.data.articles.mapToListOfNewsEntity())
                    ResponseWrapper.Success(result.data.articles.mapToListOfNews())
                }
            }
        }

    }
}