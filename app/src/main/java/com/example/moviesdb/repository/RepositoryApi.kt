package com.example.moviesdb.repository

import com.example.moviesdb.model.GenresResponse
import com.example.moviesdb.model.Movie
import com.example.moviesdb.model.MovieSimilar
import com.example.moviesdb.model.ListaFilmes
import com.example.moviesdb.network.EndPoints
import com.example.moviesdb.network.RetrofitInit

class RepositoryApi {

    companion object{
        const val chave = "875d4cd8158a587a94002943424d5f28"
    }

    private var url = "https://api.themoviedb.org/3/"

    private var service = EndPoints::class

    private val conectionService = RetrofitInit(url).create(service)

    suspend fun buscarFilmeApi(idFilme: Int): Movie = conectionService.buscarFilmes(idFilme)

    suspend fun buscarFilmesSemelhantesApi(idFilme: Int): MovieSimilar = conectionService.buscarFilmesSemelhantes(idFilme)

    suspend fun buscarGenerosApi(): GenresResponse = conectionService.buscarGeneros()

    suspend fun buscarListaDeFilmesApi(): ListaFilmes = conectionService.buscarListaDeFilmes(1)
}


