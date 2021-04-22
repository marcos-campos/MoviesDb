package com.example.moviesdb.network

import com.example.moviesdb.model.GenresResponse
import com.example.moviesdb.model.Movie
import com.example.moviesdb.modelMovieSimilar.MovieSimilar
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