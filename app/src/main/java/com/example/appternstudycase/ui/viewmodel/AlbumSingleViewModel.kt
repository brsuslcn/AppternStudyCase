package com.example.appternstudycase.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appternstudycase.data.model.tracks_model.Data
import com.example.appternstudycase.data.model.tracks_model.TracksModel
import com.example.appternstudycase.data.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumSingleViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {

    private val _tracksLiveData = MutableLiveData<List<Data>>()
    val tracksLiveData : LiveData<List<Data>> get() = _tracksLiveData

    fun songs(albumId : Int)
    {
        viewModelScope.launch {
            val response = apiRepository.tracks(albumId)
            val songItems : TracksModel? = response.body()
            _tracksLiveData.value = songItems?.data

        }
    }
}