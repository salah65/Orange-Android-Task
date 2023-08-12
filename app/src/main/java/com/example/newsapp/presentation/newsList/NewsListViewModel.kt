package com.example.newsapp.presentation.newsList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.network.ResponseWrapper
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.useCases.GetAllNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(private val getAllNewsUseCase: GetAllNewsUseCase) :
    ViewModel() {
    private val _getNewsMutableFlow: MutableStateFlow<List<Article>> = MutableStateFlow(emptyList())
    val getNewsFlow: StateFlow<List<Article>> = _getNewsMutableFlow

    private val _showErrorMutableFlow: MutableSharedFlow<String> = MutableSharedFlow()
    val showErrorFlow: SharedFlow<String> = _showErrorMutableFlow

    init {
        getNews("tesla")
    }

    fun getNews(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val news = getAllNewsUseCase(query)) {
                is ResponseWrapper.Error -> _showErrorMutableFlow.emit(news.message ?: "")
                is ResponseWrapper.Success -> _getNewsMutableFlow.value = news.data
            }
        }
    }
}