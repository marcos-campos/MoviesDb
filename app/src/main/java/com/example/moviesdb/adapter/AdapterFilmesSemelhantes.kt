package com.example.moviesdb.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesdb.R
import com.example.moviesdb.model.Genre
import com.example.moviesdb.model.MovieSimilarResponse
import com.squareup.picasso.Picasso
import java.time.LocalDate

class AdapterFilmesSemelhantes(
        private val listaFilmesSemelhantes: MutableList<MovieSimilarResponse>,
        private val context: Context,
        private val listaGeneros: MutableList<Genre>
        ): RecyclerView.Adapter<ViewHolderFilmesSemelhantes>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFilmesSemelhantes {
        val view = LayoutInflater.from(context).inflate(R.layout.main_recycler_movies, parent, false)
        return ViewHolderFilmesSemelhantes(view)
    }

    override fun getItemCount(): Int = listaFilmesSemelhantes.size

    override fun onBindViewHolder(holder: ViewHolderFilmesSemelhantes, position: Int) {
        val nomeFilmeSemelhante = holder.nomeDoFilme
        nomeFilmeSemelhante.text = listaFilmesSemelhantes[position].title.toString()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val dateComplete = LocalDate.parse(listaFilmesSemelhantes[position].releaseDate)
            val year = dateComplete.year

            val anoFilmeSemelhante = holder.anoDoFilme
            anoFilmeSemelhante.text = year.toString()
        }

        val primeiroGenero = listaFilmesSemelhantes[position].genreIds?.get(0) ?: 0
        val segundoGenero = listaFilmesSemelhantes[position].genreIds?.get(1) ?: 0


        val firstGenre = listaGeneros.firstOrNull {
            it.id == primeiroGenero
        }

        val secondGenre = listaGeneros.firstOrNull {
            it.id == segundoGenero
        }

        firstGenre?.let {genero1 ->

            val generoFilmeSemelhante = holder.generoDoFilme
            generoFilmeSemelhante.text = genero1.name

            secondGenre?.let {genero2 ->
                generoFilmeSemelhante.text = genero1.name + ", " + genero2.name
            }

        }


        val baseUrl = "https://image.tmdb.org/t/p/"
        val tamanhoImage = "w500/"

        val imagemFilmeSemelhante = holder.imagemDoFilme
        Picasso.with(context).load(baseUrl + tamanhoImage + listaFilmesSemelhantes[position].posterPath).into(imagemFilmeSemelhante)
    }
}
