package com.example.moviesdb.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesdb.R
import com.example.moviesdb.ui.main.model.Movie
import com.example.moviesdb.ui.main.modelMovieSimilar.Result
import com.squareup.picasso.Picasso

class AdapterFilmesSemelhantes(
        private val listaFilmesSemelhantes: MutableList<Result>,
        private val context: Context
        ): RecyclerView.Adapter<ViewHolderFilmesSemelhantes>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFilmesSemelhantes {
        val view = LayoutInflater.from(context).inflate(R.layout.main_recycler_movies, parent, false)
        return ViewHolderFilmesSemelhantes(view)
    }

    override fun getItemCount(): Int = listaFilmesSemelhantes.size


    override fun onBindViewHolder(holder: ViewHolderFilmesSemelhantes, position: Int) {
        val nomeFilmeSemelhante = holder.nomeDoFilme
        nomeFilmeSemelhante.text = listaFilmesSemelhantes[position].title.toString()

        val anoFilmeSemelhante = holder.anoDoFilme
        anoFilmeSemelhante.text = listaFilmesSemelhantes[position].releaseDate.toString()

        val generoFilmeSemelhante = holder.generoDoFilme
        generoFilmeSemelhante.text = listaFilmesSemelhantes[position].voteAverage.toString()

        val baseUrl = "https://image.tmdb.org/t/p/"
        val tamanhoImage = "w500/"

        val imagemFilmeSemelhante = holder.imagemDoFilme
        Picasso.with(context).load(baseUrl + tamanhoImage + listaFilmesSemelhantes[position].posterPath).into(imagemFilmeSemelhante)
    }
}
