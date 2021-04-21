package com.example.moviesdb.ui.main.network

import com.example.moviesdb.ui.main.model.Movie
import retrofit2.http.GET
import retrofit2.http.Path

interface EndPoints {

    @GET("movie/{movie_id}")
    suspend fun buscarFilmes(
            @Path("movie_id") movie_id: Int
    ): Movie

}