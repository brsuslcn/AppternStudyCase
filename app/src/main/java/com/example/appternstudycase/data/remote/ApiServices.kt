package com.example.appternstudycase.data.remote

import com.example.appternstudycase.data.model.albums_model.AlbumsModel
import com.example.appternstudycase.data.model.arists_model.ArtistsModel
import com.example.appternstudycase.data.model.categories_model.CategoriesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {
     @GET("genre")
     suspend fun getCategories() : Response<CategoriesModel>

     @GET("genre/{id}/artists")
     suspend fun getArtists(@Path("id") id:Int) : Response<ArtistsModel>

     @GET("artist/{artist_id}/albums")
     suspend fun getAlbums(@Path("artist_id") artistId:Int) : Response<AlbumsModel>
}