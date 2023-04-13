package com.sarang.torang.di

import androidx.lifecycle.LiveData
import com.example.torang_core.data.AppDatabase
import com.example.torang_core.data.dao.*
import com.example.torang_core.data.model.LoggedInUserData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DaoModules {
    /** 로컬 데이터베이스의 사용자 로그인 관리 DAO 제공 */
    @Provides
    fun provideLoggedInUserDao(appDatabase: AppDatabase): LoggedInUserDao {
        return appDatabase.LoggedInUserDao()
    }

    /** 로컬 데이터베이스의 사용자 관리 DAO 제공 */
    @Provides
    fun providefeedDao(appDatabase: AppDatabase): FeedDao {
        return appDatabase.feedDao()
    }

    /** 로컬 데이터베이스의 사용자 관리 DAO 제공 */
    @Provides
    fun provideCommentDao(appDatabase: AppDatabase): CommentDao {
        return appDatabase.commentDao()
    }

    /** 로컬 데이터베이스의 사용자 관리 DAO 제공 */
    @Provides
    fun provideReviewDao(appDatabase: AppDatabase): ReviewDao {
        return appDatabase.reviewDao()
    }

    /** 로컬 데이터베이스의 사용자 관리 DAO 제공 */
    @Provides
    fun provideMyReviewDao(appDatabase: AppDatabase): MyReviewDao {
        return appDatabase.myReviewDao()
    }

    /** 로컬 데이터베이스의 사용자 관리 DAO 제공 */
    @Provides
    fun providePictureDao(appDatabase: AppDatabase): PictureDao {
        return appDatabase.pictureDao()
    }
}