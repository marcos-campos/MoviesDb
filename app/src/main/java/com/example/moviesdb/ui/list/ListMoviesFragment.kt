package com.example.moviesdb.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesdb.R
import com.example.moviesdb.adapter.AdapterFilmesSemelhantes
import com.example.moviesdb.adapter.AdapterListaFilmes
import com.example.moviesdb.model.MovieSimilarResponse
import com.example.moviesdb.modelListaFilmes.Item
import com.example.moviesdb.modelListaFilmes.ListaFilmes
import com.example.moviesdb.ui.main.MainFragment
import com.example.moviesdb.ui.main.MainViewModel
import com.squareup.picasso.Picasso

class ListMoviesFragment : Fragment() {

    private var listaDeFilmes = mutableListOf<Item>()
    val recycler1 by lazy {view?.findViewById<RecyclerView>(R.id.recycler_list)}

    companion object {
        fun newInstance() = ListMoviesFragment()
    }

    private lateinit var viewModel: ListMoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_movies, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListMoviesViewModel::class.java)

        viewModel.buscarListaDeFilmesCoroutines()

        recycler1?.layoutManager = LinearLayoutManager(activity)

        viewModel.listaFilmesLiveData.observe(this, Observer {
            it.items?.let { it1 -> listaDeFilmes.addAll(it1) }

            val adapter = activity?.let { AdapterListaFilmes(listaDeFilmes, it) }
            recycler1?.adapter = adapter

            listaDeFilmes

            adapter?.notifyDataSetChanged()

        })
    }
}