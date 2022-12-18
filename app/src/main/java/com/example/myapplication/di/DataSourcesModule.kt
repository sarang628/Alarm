package com.example.myapplication.di

import com.example.torang_core.datasource.local.MyReviewsLocalDataSource
import com.example.torang_core.datasource.local.MyReviewsRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourcesModule {
//    @Binds
//    abstract fun provideLocalDataSource(myReviewsLocalDataSourceImpl: MyReviewsLocalDataSourceImpl): MyReviewsLocalDataSource

//    @Binds
//    abstract fun provideRemoteDataSource(myReviewsRemoteDataSourceImpl: MyReviewsRemoteDataSourceImpl): MyReviewsRemoteDataSource
}