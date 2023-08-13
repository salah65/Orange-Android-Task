package com.example.newsapp.data.localDb.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val coverUrl: String,
    val headLine: String,
    val content: String,
    val sourceName: String,
    val description: String,
    val author: String,
    val date: String,
    val articleURL: String
)