package com.example.moviesdb.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesdb.R

class ViewHolderFilmesSemelhantes (view: View) : RecyclerView.ViewHolder(view) {

    val nomeDoFilme by lazy { view.findViewById<TextView>(R.id.recycler_titulo_filme) }
    val anoDoFilme by lazy { view.findViewById<TextView>(R.id.recycler_ano_filme) }
    val generoDoFilme by lazy { view.findViewById<TextView>(R.id.recycler_genero_filme) }
    val imagemDoFilme by lazy { view.findViewById<ImageView>(R.id.recycler_imagem_filme) }

}
