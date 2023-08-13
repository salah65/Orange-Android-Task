package com.example.newsapp.data.network

import com.example.newsapp.data.network.response.BaseResponse
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

suspend fun <T : BaseResponse> apiCallHandler(
    dispatcher: CoroutineDispatcher = Dispatchers.IO, apiCall: suspend () -> T
): ResponseWrapper<T> {
    return withContext(dispatcher) {
        try {
            val response = apiCall.invoke()
            if (response.status == "ok") {
                ResponseWrapper.Success(response)
            } else {
                ResponseWrapper.Error(message = response.message)
            }
        } catch (throwable: Throwable) {
            val errorString = (throwable as? HttpException)?.response()?.errorBody()?.string()
             try {
                val baseErrorDTO = Gson().fromJson(errorString, BaseResponse::class.java)
                ResponseWrapper.Error(message = baseErrorDTO.message!!)
            } catch (e: Exception) {
                ResponseWrapper.Error(message = "There is an error happen, please try again later")
            }

        }
    }
}
