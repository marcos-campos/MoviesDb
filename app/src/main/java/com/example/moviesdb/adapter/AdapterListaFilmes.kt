package com.example.moviesdb.adapter

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesdb.MainActivity
import com.example.moviesdb.R
import com.example.moviesdb.model.Genre
import com.example.moviesdb.model.MovieSimilarResponse
import com.example.moviesdb.modelListaFilmes.Item
import com.example.moviesdb.modelListaFilmes.ListaFilmes
import com.example.moviesdb.ui.main.MainFragment
import com.squareup.picasso.Picasso
import java.time.LocalDate

class AdapterListaFilmes (
        private val listaFilmes: MutableList<Item>,
        private val context: Context
): RecyclerView.Adapter<ViewHolderListaFilmes>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderListaFilmes {
        val view = LayoutInflater.from(context).inflate(R.layout.main_recycler_listafilmes, parent, false)
        return ViewHolderListaFilmes(view)
    }

    override fun getItemCount(): Int = listaFilmes.size

    override fun onBindViewHolder(holder: ViewHolderListaFilmes, position: Int) {

        val nomeDoFilme = holder.nomeFilme
        nomeDoFilme.text = listaFilmes[position].title.toString()

        val baseUrl = "https://image.tmdb.org/t/p/"
        val tamanhoImage = "w500/"

        val imagemFilme = holder.imagemFilme
        Picasso.with(context).load(baseUrl + tamanhoImage + listaFilmes[position].posterPath).into(imagemFilme)

        holder.itemView.setOnClickListener {
            val intent = Intent (it.context, MainActivity::class.java)

            intent.putExtra("dadosFilmes", listaFilmes[position])

            it.context.startActivity(intent)
        }
    }
}