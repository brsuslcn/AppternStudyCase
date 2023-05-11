package com.example.appternstudycase.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.appternstudycase.data.model.sql_likes_model.TracksLikesModel

@Dao
interface LikesDao {
    @Query("SELECT *FROM tracks")
    suspend fun allLikes() : List<TracksLikesModel>

    @Insert
    suspend fun like(track : TracksLikesModel)

    @Delete
    suspend fun dislike(track : TracksLikesModel)

}