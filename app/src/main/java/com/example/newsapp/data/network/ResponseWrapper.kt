package com.example.newsapp.data.network

sealed class ResponseWrapper<out T> {
    data class Success<out T>(val data: T) : ResponseWrapper<T>()
    data class Error(val code: Int? = null, val message: String? = null) : ResponseWrapper<Nothing>()
}