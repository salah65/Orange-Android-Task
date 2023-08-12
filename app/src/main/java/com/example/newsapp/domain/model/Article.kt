package com.example.newsapp.domain.model

data class Article(
    val coverUrl: String,
    val headLine: String,
    val content: String,
    val sourceName: String,
    val description: String,
    val author: String,
    val date: String
)