package com.example.moviesdb.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesdb.model.GenresResponse
import com.example.moviesdb.model.Movie
import com.example.moviesdb.model.MovieSimilarResponse
import com.example.moviesdb.modelListaFilmes.ListaFilmes
import com.example.moviesdb.repository.RepositoryApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class MainViewModel : ViewModel() {

    val movieLiveData = MutableLiveData<Movie>()
    val genresLiveData = MutableLiveData<GenresResponse>()
    val movieSimilarLiveData = MutableLiveData<ArrayList<MovieSimilarResponse>>()
    val repository = RepositoryApi()
    val loadingFilmes = MutableLiveData<Boolean>()
    val loadingFilmesSemelhantes = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    fun buscarFilmesCoroutines(idFilme: Int){
        CoroutineScope(Dispatchers.IO).launch {
            loadingFilmes.postValue(true)

            try {
                repository.buscarFilmeApi(idFilme).let {
                    movieLiveData.postValue(it)
                    loadingFilmes.postValue(false) }
            }

            catch (error: Throwable) {
                loadingFilmes.postValue(false)
                enviarErro(error)
            }
        }
    }

    fun buscarFilmesSemelhantesCoroutines(idFilme: Int){
        CoroutineScope(Dispatchers.IO).launch {
            loadingFilmesSemelhantes.postValue(true)

            try {
                repository.buscarFilmesSemelhantesApi(idFilme).let {
                    movieSimilarLiveData.postValue((it.results as ArrayList<MovieSimilarResponse>?)!!)
                    loadingFilmesSemelhantes.postValue(false) }
            }

            catch (error: Throwable) {
                loadingFilmesSemelhantes.postValue(false)
                enviarErro(error)
            }
        }
    }

    fun buscarGenerosCoroutines(){
        CoroutineScope(Dispatchers.IO).launch {

            try {
                repository.buscarGenerosApi().let {
                    genresLiveData.postValue(it)
                }
            }

            catch (error: Throwable) {
                enviarErro(error)
            }
        }
    }

    private fun enviarErro(error: Throwable) {
        when(error){
            is UnknownHostException -> errorMessage.postValue("verifique sua conexão")
        }
    }
}