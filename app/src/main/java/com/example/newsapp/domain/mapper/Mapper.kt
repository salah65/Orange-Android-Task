package com.example.newsapp.domain.mapper

import com.example.newsapp.data.localDb.entities.ArticleEntity
import com.example.newsapp.data.network.response.ArticlesItem
import com.example.newsapp.domain.model.Article

fun List<ArticlesItem?>?.mapToListOfNews(): List<Article> {
    return this?.map {
        Article(
            it!!.urlToImage ?: "",
            it.title ?: "",
            it.content ?: "",
            it.source?.name ?: "",
            it.description ?: "",
            it.author ?: "",
            it.publishedAt ?: "",
            it.url ?: ""
        )
    } ?: emptyList()
}

fun List<ArticlesItem?>?.mapToListOfNewsEntity(): List<ArticleEntity> {
    return this?.map {
        ArticleEntity(
            coverUrl = it!!.urlToImage ?: "",
            headLine = it.title ?: "",
            content = it.content ?: "",
            sourceName = it.source?.name ?: "",
            description = it.description ?: "",
            author = it.author ?: "",
            date = it.publishedAt ?: "",
            articleURL = it.url ?: ""
        )
    } ?: emptyList()
}
fun List<ArticleEntity?>?.mapToListOfArticles(): List<Article> {
    return this?.map {
        Article(
            coverUrl = it!!.coverUrl ?: "",
            headLine = it.headLine ?: "",
            content = it.content ?: "",
            sourceName = it.sourceName ?: "",
            description = it.description ?: "",
            author = it.author ?: "",
            date = it.date ?: "",
            articleURL = it.articleURL ?: ""
        )
    } ?: emptyList()
}