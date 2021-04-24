package com.example.moviesdb.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesdb.R

class ViewHolderListaFilmes  (view: View) : RecyclerView.ViewHolder(view) {

    val nomeFilme by lazy { view.findViewById<TextView>(R.id.recycler_listafilmes_titulo) }
    val imagemFilme by lazy { view.findViewById<ImageView>(R.id.recycler_listafilmes_imagem) }

}