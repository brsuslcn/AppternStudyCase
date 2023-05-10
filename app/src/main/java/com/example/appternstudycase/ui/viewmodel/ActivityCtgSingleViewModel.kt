package com.example.appternstudycase.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appternstudycase.data.model.arists_model.ArtistsModel
import com.example.appternstudycase.data.model.arists_model.Data
import com.example.appternstudycase.data.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityCtgSingleViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {
    private val _artistsLiveData = MutableLiveData<List<Data>>()
    val artistsLiveData : LiveData<List<Data>> get() = _artistsLiveData

    fun artists(genreId : Int)
    {
        viewModelScope.launch {
            val response = apiRepository.artists(genreId)
            val artistItems : ArtistsModel? = response.body()
            _artistsLiveData.value = artistItems?.data
        }
    }
}