package com.example.moviesdb.ui.main.network

import com.example.moviesdb.ui.main.model.GenresResponse
import com.example.moviesdb.ui.main.model.Movie
import com.example.moviesdb.ui.main.modelMovieSimilar.MovieSimilar
import com.example.moviesdb.ui.main.modelMovieSimilar.Result
import retrofit2.http.GET
import retrofit2.http.Path

interface EndPoints {

    @GET("movie/{movie_id}")
    suspend fun buscarFilmes(
            @Path("movie_id") movie_id: Int
    ): Movie

    @GET("movie/{movie_id}/similar")
    suspend fun buscarFilmesSemelhantes(
            @Path("movie_id") movie_id: Int
    ): MovieSimilar

    @GET("genre/movie/list")
    suspend fun buscarGeneros(
    ): GenresResponse

}