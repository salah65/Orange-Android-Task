package com.example.newsapp.data.di

import com.example.newsapp.data.network.Endpoints
import com.example.newsapp.data.repositoryImplementation.NewsRepositoryImp
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.domain.useCases.GetAllNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun provideNewsRepository(endpoints: Endpoints): NewsRepository {
        return NewsRepositoryImp(endpoints)
    }

    @Singleton
    @Provides
    fun provideGetNewsUseCase(newsRepository: NewsRepository): GetAllNewsUseCase {
        return GetAllNewsUseCase(newsRepository)
    }


}