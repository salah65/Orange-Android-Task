package com.example.newsapp.data.network

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> apiCallHandler(
    dispatcher: CoroutineDispatcher = Dispatchers.IO, apiCall: suspend () -> T
): ResponseWrapper<T> {
    return withContext(dispatcher) {
        try {
            ResponseWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            ResponseWrapper.Error(message = throwable.localizedMessage)
        }
    }
}
