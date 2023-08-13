package com.example.newsapp.data.di

import android.app.Application
import androidx.room.Room
import com.example.newsapp.data.localDb.DataBase
import com.example.newsapp.data.localDb.dao.ArticlesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): DataBase {
        return Room.databaseBuilder(
            application, DataBase::class.java, "my-database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideItemDao(database: DataBase): ArticlesDao {
        return database.articlesDao()
    }
}