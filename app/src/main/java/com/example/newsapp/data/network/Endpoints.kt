package com.example.newsapp.data.network

import com.example.newsapp.data.network.response.GetAllNewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoints {
    @GET("everything")
    suspend fun getNewsList(
        @Query("q") query: String, @Query("sortBy") sortBy: String
    ): GetAllNewsResponse
}