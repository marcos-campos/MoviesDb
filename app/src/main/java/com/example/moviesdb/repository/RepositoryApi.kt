package com.example.moviesdb.repository

import com.example.moviesdb.model.GenresResponse
import com.example.moviesdb.model.Movie
import com.example.moviesdb.modelMovieSimilar.MovieSimilar
import com.example.moviesdb.network.EndPoints
import com.example.moviesdb.network.RetrofitInit

class RepositoryApi {

    companion object{
        const val chave = "875d4cd8158a587a94002943424d5f28"
    }

    private var url = "https://api.themoviedb.org/3/"

    private var service = EndPoints::class

    private val conectionService = RetrofitInit(url).create(service)

    suspend fun buscarFilmeApi(): Movie = conectionService.buscarFilmes(200)

    suspend fun buscarFilmesSemelhantesApi(): MovieSimilar = conectionService.buscarFilmesSemelhantes(200)

    suspend fun buscarGenerosApi(): GenresResponse = conectionService.buscarGeneros()
}