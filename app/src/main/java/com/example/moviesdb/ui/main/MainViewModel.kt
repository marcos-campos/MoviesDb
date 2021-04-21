package com.example.moviesdb.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesdb.ui.main.model.Movie
import com.example.moviesdb.ui.main.modelMovieSimilar.MovieSimilar
import com.example.moviesdb.ui.main.modelMovieSimilar.Result
import com.example.moviesdb.ui.main.repository.RepositoryApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException

class MainViewModel : ViewModel() {

    val movieLiveData = MutableLiveData<Movie>()
    val movieSimilarLiveData = MutableLiveData<ArrayList<Result>>()
    val repository = RepositoryApi()
    val loadingFilmes = MutableLiveData<Boolean>()
    val loadingFilmesSemelhantes = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    fun buscarFilmesCoroutines(){
        CoroutineScope(Dispatchers.IO).launch {
            loadingFilmes.postValue(true)

            try {
                repository.buscarFilmeApi().let {
                    movieLiveData.postValue(it)
                    loadingFilmes.postValue(false) }
            }

            catch (error: Throwable) {
                loadingFilmes.postValue(false)
                enviarErro(error)
            }
        }
    }

    fun buscarFilmesSemelhantesCoroutines(){
        CoroutineScope(Dispatchers.IO).launch {
            loadingFilmesSemelhantes.postValue(true)

            try {
                repository.buscarFilmesSemelhantesApi().let {
                    movieSimilarLiveData.postValue((it.results as ArrayList<Result>?)!!)
                    loadingFilmesSemelhantes.postValue(false) }
            }

            catch (error: Throwable) {
                loadingFilmesSemelhantes.postValue(false)
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