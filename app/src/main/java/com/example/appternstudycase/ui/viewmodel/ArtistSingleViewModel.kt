package com.example.appternstudycase.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appternstudycase.data.model.albums_model.AlbumsModel
import com.example.appternstudycase.data.model.albums_model.Data
import com.example.appternstudycase.data.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistSingleViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel(){

    private val _albumsLiveData = MutableLiveData<List<Data>>()
    val albumsLiveData : LiveData<List<Data>> get() = _albumsLiveData


    fun albums(artistId : Int)
    {

        viewModelScope.launch {
            try{
                val response = apiRepository.albums(artistId)
                val albumItems : AlbumsModel? = response.body()
                _albumsLiveData.value = albumItems?.data

            }catch (e:Exception)
            {
               Log.e("Connection Error", e.toString())
            }

        }
    }
}