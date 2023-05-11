package com.example.appternstudycase.data.model.sql_likes_model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "tracks")
data class TracksLikesModel(
    @PrimaryKey val track_id : Int,
    @ColumnInfo(name = "track_title") @NotNull var track_title : String,
    @ColumnInfo(name = "track_duration") @NotNull var track_duration : String,
    @ColumnInfo(name = "track_picture") @NotNull var track_picture : String,
)
