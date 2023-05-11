package com.example.appternstudycase.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appternstudycase.data.model.sql_likes_model.TracksLikesModel

@Database(entities = [TracksLikesModel::class], version = 1)
abstract class SqlDatabase : RoomDatabase() {
    abstract fun getLikesDao() : LikesDao
}