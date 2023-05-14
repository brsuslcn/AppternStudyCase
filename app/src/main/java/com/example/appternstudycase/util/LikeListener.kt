package com.example.appternstudycase.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.appternstudycase.data.model.sql_likes_model.TracksLikesModel


interface LikeListener {
    fun getLikes() : MutableLiveData<List<TracksLikesModel>>
    fun likeTrack(track : TracksLikesModel)
    fun dislikeTrack(track : TracksLikesModel)
}