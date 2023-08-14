package com.example.newsapp.data.localDb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapp.data.localDb.dao.ArticlesDao
import com.example.newsapp.data.localDb.entities.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1, exportSchema = false)
abstract class DataBase():RoomDatabase() {
    abstract fun articlesDao():ArticlesDao
}