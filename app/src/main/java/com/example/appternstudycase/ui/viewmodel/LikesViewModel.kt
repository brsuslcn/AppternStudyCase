package com.example.appternstudycase.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appternstudycase.data.model.sql_likes_model.TracksLikesModel
import com.example.appternstudycase.data.repository.DbLikesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LikesViewModel @Inject constructor(private val dbLikesRepository: DbLikesRepository) : ViewModel() {
    var likedData = MutableLiveData<List<TracksLikesModel>>()

    init{
        dbLikes()
        likedData = dbLikesRepository.getLikes()
    }

    fun dbLikes()
    {
        dbLikesRepository.getAllLikes()
    }

    fun likeTrack(track : TracksLikesModel)
    {
        dbLikesRepository.likeTrack(track)
    }


    fun dislikeTrack(track : TracksLikesModel)
    {
        dbLikesRepository.dislikeTrack(track)
    }
}