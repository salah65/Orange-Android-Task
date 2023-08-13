package com.example.newsapp.data.localDb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.data.localDb.entities.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticlesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<ArticleEntity>)

    @Query("SELECT * FROM articles WHERE headLine LIKE '%' || :query || '%'")
    fun getItems(query:String): List<ArticleEntity>
}