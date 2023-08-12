package com.example.newsapp.presentation.newsList

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.useCases.GetAllNewsUseCase
import com.example.newsapp.presentation.core.ArticleDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(private val getAllNewsUseCase: GetAllNewsUseCase) :
    ViewModel() {
    private val _searchQueryMutableFlow: MutableStateFlow<String> = MutableStateFlow("tesla")
    val getNewsFlow: Flow<PagingData<Article>> = _searchQueryMutableFlow.flatMapLatest { query ->
        Pager(PagingConfig(pageSize = 10, initialLoadSize = 10, enablePlaceholders = true)) {
            ArticleDataSource(getAllNewsUseCase, query)
        }.flow
    }

    fun setSearchQuery(query: String) {
        _searchQueryMutableFlow.value = query
    }
}