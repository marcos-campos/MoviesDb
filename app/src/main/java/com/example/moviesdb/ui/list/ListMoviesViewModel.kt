package com.example.moviesdb.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesdb.model.ListaFilmes
import com.example.moviesdb.repository.RepositoryApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class ListMoviesViewModel : ViewModel() {

    val listaFilmesLiveData = MutableLiveData<ListaFilmes>()
    val repository = RepositoryApi()
    val errorMessage = MutableLiveData<String>()

    fun buscarListaDeFilmesCoroutines(){
        CoroutineScope(Dispatchers.IO).launch {

            try {
                repository.buscarListaDeFilmesApi().let {
                    listaFilmesLiveData.postValue(it)
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