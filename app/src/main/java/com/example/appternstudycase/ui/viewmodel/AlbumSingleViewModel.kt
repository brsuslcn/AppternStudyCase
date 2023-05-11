package com.example.appternstudycase.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appternstudycase.data.model.sql_likes_model.TracksLikesModel
import com.example.appternstudycase.data.model.tracks_model.Data
import com.example.appternstudycase.data.model.tracks_model.TracksModel
import com.example.appternstudycase.data.repository.ApiRepository
import com.example.appternstudycase.data.repository.DbLikesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumSingleViewModel @Inject constructor(private val apiRepository: ApiRepository, private val dbLikesRepository: DbLikesRepository) : ViewModel() {

    private val _tracksLiveData = MutableLiveData<List<Data>>()
    val tracksLiveData : LiveData<List<Data>> get() = _tracksLiveData

    var likedData = MutableLiveData<List<TracksLikesModel>>()

    init{
        dbLikes()
        likedData = dbLikesRepository.getLikes()
    }

    fun songs(albumId : Int)
    {
        viewModelScope.launch {
            val response = apiRepository.tracks(albumId)
            val songItems : TracksModel? = response.body()
            _tracksLiveData.value = songItems?.data
        }
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