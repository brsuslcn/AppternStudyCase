package com.example.appternstudycase.data.repository

import com.example.appternstudycase.data.model.arists_model.ArtistsModel
import com.example.appternstudycase.data.model.categories_model.CategoriesModel
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
}