package com.example.moviesdb.ui.main.repository

import com.example.moviesdb.ui.main.model.Movie
import com.example.moviesdb.ui.main.modelMovieSimilar.MovieSimilar
import com.example.moviesdb.ui.main.modelMovieSimilar.Result
import com.example.moviesdb.ui.main.network.EndPoints
import com.example.moviesdb.ui.main.network.RetrofitInit

class RepositoryApi {

    companion object{
        const val chave = "875d4cd8158a587a94002943424d5f28"
    }

    private var url = "https://api.themoviedb.org/3/"

    private var service = EndPoints::class

    private val conectionService = RetrofitInit(url).create(service)

    suspend fun buscarFilmeApi(): Movie = conectionService.buscarFilmes(200)
    suspend fun buscarFilmesSemelhantesApi(): MovieSimilar = conectionService.buscarFilmesSemelhantes(200)
}