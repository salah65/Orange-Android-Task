package com.example.newsapp.domain.mapper

import com.example.newsapp.data.network.response.ArticlesItem
import com.example.newsapp.domain.model.Article

fun List<ArticlesItem?>?.mapToListOfNews(): List<Article> {
    return this?.map {
        Article(
            it!!.urlToImage?:"",
            it.description?:"",
            it.content?:"",
            it.source?.name?:"",
            it.description?:"",
            it.author?:"",
            it.publishedAt?:""
        )
    } ?: emptyList()
}