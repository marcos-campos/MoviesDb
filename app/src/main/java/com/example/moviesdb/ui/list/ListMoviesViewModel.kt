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

    var nextPage = 0

    fun buscarListaDeFilmesCoroutines(){
        CoroutineScope(Dispatchers.IO).launch {

            try {
                repository.buscarListaDeFilmesApi(1).let {
//                    atualizarPaginacao(it.id?.toInt())
                    listaFilmesLiveData.postValue(it)
                }
            }

            catch (error: Throwable) {
                enviarErro(error)
            }
        }
    }

    fun atualizarPaginacao(id: Int?) {
        nextPage = id?.plus(1) ?: 1
    }

    fun buscarNovaListaDeFilmes(){
        CoroutineScope(Dispatchers.IO).launch {

            try {
                atualizarPaginacao(nextPage)
                repository.buscarListaDeFilmesApi(nextPage).let {
                    atualizarPaginacao(it.id?.toInt())
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