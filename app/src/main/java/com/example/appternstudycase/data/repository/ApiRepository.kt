package com.example.appternstudycase.data.repository

import com.example.appternstudycase.data.model.albums_model.AlbumsModel
import com.example.appternstudycase.data.model.arists_model.ArtistsModel
import com.example.appternstudycase.data.model.categories_model.CategoriesModel
import com.example.appternstudycase.data.model.tracks_model.TracksModel
import com.example.appternstudycase.data.remote.ApiServices
import retrofit2.Response
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService : ApiServices) {
    suspend fun categories() : Response<CategoriesModel>{
        return apiService.getCategories()
    }

    suspend fun artists(genreId : Int) : Response<ArtistsModel>{
        return apiService.getArtists(genreId)
    }

    suspend fun albums(artistId : Int) : Response<AlbumsModel>{
        return apiService.getAlbums(artistId)
    }

    suspend fun tracks(albumId: Int) : Response<TracksModel>{
        return apiService.getTracks(albumId)
    }
}