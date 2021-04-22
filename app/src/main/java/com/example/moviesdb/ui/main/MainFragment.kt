package com.example.moviesdb.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesdb.R
import com.example.moviesdb.ui.main.adapter.AdapterFilmesSemelhantes
import com.example.moviesdb.ui.main.model.Genre
import com.example.moviesdb.ui.main.model.Movie
import com.example.moviesdb.ui.main.modelMovieSimilar.Result
import com.squareup.picasso.Picasso

class MainFragment : Fragment() {
    
    val tituloFilme by lazy {view?.findViewById<TextView>(R.id.tv_titulo)}
    val imagemFilme by lazy { view?.findViewById<ImageView>(R.id.iv_filme) }
    val viewsFilme by lazy { view?.findViewById<TextView>(R.id.tv_likes) }
    val likesFilmes by lazy { view?.findViewById<TextView>(R.id.tv_note) }
    val coracaoVazio by lazy {view?.findViewById<ImageView>(R.id.coracao_vazio)}
    val coracaoPreenchido by lazy {view?.findViewById<ImageView>(R.id.coracao_preenchido)}
    val botaoRetornar by lazy {view?.findViewById<Button>(R.id.btn_retornar)}

    val recycler by lazy {view?.findViewById<RecyclerView>(R.id.recycler_main)}
    private var listaFilmeSimilar = mutableListOf<Result>()

    private var listaGeneros = mutableListOf<Genre>()

    lateinit var progressBarFilmes : ProgressBar
    lateinit var progressBarFilmesSemelhantes : ProgressBar

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        progressBarFilmes = view!!.findViewById<ProgressBar>(R.id.progress_bar_filmes)
        progressBarFilmesSemelhantes = view!!.findViewById<ProgressBar>(R.id.progress_bar_filmes_semelhantes)

        botaoRetornar?.setOnClickListener {
            activity?.onBackPressed()
        }

        viewModel.buscarFilmesCoroutines()
        viewModel.movieLiveData.observe(this, Observer {

            val baseUrlImage = "https://image.tmdb.org/t/p/"
            val tamanhoImage = "w500/"

            tituloFilme?.text = it.originalTitle
            viewsFilme?.text = it.voteCount.toString()
            likesFilmes?.text = it.popularity.toString()

            Picasso.with(activity).load(baseUrlImage + tamanhoImage + it.posterPath).fit().into(imagemFilme)

            setClickListeners()

        })

        recycler?.layoutManager = LinearLayoutManager(activity)

        viewModel.buscarGenerosCoroutines()
        viewModel.genresLiveData.observe(this, Observer {
            listaGeneros.addAll(it.listaGeneros)

            viewModel.buscarFilmesSemelhantesCoroutines()
        })

        viewModel.movieSimilarLiveData.observe(this, Observer {
            listaFilmeSimilar.addAll(it)

            val adapter = activity?.let { AdapterFilmesSemelhantes(listaFilmeSimilar, it, listaGeneros) }
            recycler?.adapter = adapter

            adapter?.notifyDataSetChanged()

        })

        viewModel.loadingFilmes.observe(this, Observer {
            if (it) {
                progressBarFilmes.visibility = View.VISIBLE
            } else {
                progressBarFilmes.visibility = View.GONE
            }
        })

        viewModel.loadingFilmesSemelhantes.observe(this, Observer {
            if (it) {
                progressBarFilmesSemelhantes.visibility = View.VISIBLE
            } else {
                progressBarFilmesSemelhantes.visibility = View.GONE
            }
        })

        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })

    }

    fun setClickListeners(){
        coracaoVazio?.setOnClickListener {
            coracaoPreenchido?.visibility = View.VISIBLE
        }

        coracaoPreenchido?.setOnClickListener {
            coracaoPreenchido?.visibility = View.GONE
        }
    }
}