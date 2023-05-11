package com.example.appternstudycase.data.local

import android.content.Context
import androidx.room.Room
import com.example.appternstudycase.data.repository.DbLikesRepository
import com.example.appternstudycase.data.room.LikesDao
import com.example.appternstudycase.data.room.SqlDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideLikesDaoRepository(likesDao : LikesDao) : DbLikesRepository{
        return DbLikesRepository(likesDao)
    }

    @Provides
    @Singleton
    fun providesLikesDao(@ApplicationContext context : Context) : LikesDao{
        val vt = Room.databaseBuilder(context, SqlDatabase::class.java, "likeregisters.sqlite")
            .createFromAsset("likeregisters.sqlite").build()

        return vt.getLikesDao()
    }
}