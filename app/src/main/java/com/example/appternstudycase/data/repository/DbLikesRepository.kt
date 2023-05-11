package com.example.appternstudycase.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.appternstudycase.data.model.sql_likes_model.TracksLikesModel
import com.example.appternstudycase.data.room.LikesDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DbLikesRepository(var likesDao : LikesDao) {

    var likedList : MutableLiveData<List<TracksLikesModel>>

    init {
        likedList = MutableLiveData()
    }

    fun getLikes() : MutableLiveData<List<TracksLikesModel>>
    {
        return likedList
    }

    fun getAllLikes()
    {
        val job = CoroutineScope(Dispatchers.Main).launch {
            likedList.value=likesDao.allLikes()
        }
    }

    fun likeTrack(track : TracksLikesModel)
    {
        val job = CoroutineScope(Dispatchers.Main).launch{
            likesDao.like(track)
        }
    }

    fun dislikeTrack(track : TracksLikesModel)
    {
        val job = CoroutineScope(Dispatchers.Main).launch {
            likesDao.dislike(track)
        }
    }


}