package com.example.moviesdb.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesdb.R
import com.example.moviesdb.RecyclerScrollListener
import com.example.moviesdb.adapter.AdapterListaFilmes
import com.example.moviesdb.model.Item

class ListMoviesFragment : Fragment() {

    private var listaDeFilmes = mutableListOf<Item>()
    val recycler1 by lazy {view?.findViewById<RecyclerView>(R.id.recycler_list)}

    private val recyclerScrollListener by lazy {
        RecyclerScrollListener {
            viewModel.buscarNovaListaDeFilmes()
        }
    }

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

//        recycler1?.layoutManager = LinearLayoutManager(activity)


        val linearLayout = LinearLayoutManager(activity)
        linearLayout.orientation = LinearLayoutManager.HORIZONTAL
        recycler1?.layoutManager = linearLayout

        recycler1?.addOnScrollListener(recyclerScrollListener)

        viewModel.listaFilmesLiveData.observe(this, Observer {

            recyclerScrollListener.setRequestingNextPage(false)

            it.items?.let { it1 -> listaDeFilmes.addAll(it1) }

            val adapter = activity?.let { AdapterListaFilmes(listaDeFilmes, it) }
            recycler1?.adapter = adapter

            listaDeFilmes

            adapter?.notifyDataSetChanged()

        })
    }
}