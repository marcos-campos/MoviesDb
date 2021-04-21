package com.example.moviesdb.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesdb.ui.main.model.Movie
import com.example.moviesdb.ui.main.repository.RepositoryApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val movieLiveData = MutableLiveData<Movie>()
    val repository = RepositoryApi()

    fun buscarFilmesCoroutines(){
        CoroutineScope(Dispatchers.IO).launch {
            repository.buscarFilmeApi().let {
                movieLiveData.postValue(it)
            }
        }
    }
}