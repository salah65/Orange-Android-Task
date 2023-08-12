package com.example.newsapp.presentation.core

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.data.network.ResponseWrapper
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.useCases.GetAllNewsUseCase
import javax.inject.Inject

class ArticleDataSource(
    val getAllNewsUseCase: GetAllNewsUseCase,
    private val query: String
) : PagingSource<Int, Article>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val pageNumber = params.key ?: 1

        return when (val items = getAllNewsUseCase(query, pageNumber = pageNumber, pageSize = params.loadSize)) {
            is ResponseWrapper.Error -> LoadResult.Error(Throwable(message = items.message))
            is ResponseWrapper.Success -> LoadResult.Page(
                data = items.data,
                prevKey = if (pageNumber > 1) pageNumber - 1 else null,
                nextKey = pageNumber + 1
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        TODO("Not yet implemented")
    }
}