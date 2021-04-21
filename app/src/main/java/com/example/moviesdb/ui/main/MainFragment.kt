package com.example.moviesdb.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.moviesdb.R
import com.squareup.picasso.Picasso

class MainFragment : Fragment() {
    
    val tituloFilme by lazy {view?.findViewById<TextView>(R.id.tv_titulo)}
    val imagemFilme by lazy { view?.findViewById<ImageView>(R.id.iv_filme) }
    val viewsFilme by lazy { view?.findViewById<TextView>(R.id.tv_likes) }
    val likesFilmes by lazy { view?.findViewById<TextView>(R.id.tv_note) }
    val coracaoVazio by lazy {view?.findViewById<ImageView>(R.id.coracao_vazio)}
    val coracaoPreenchido by lazy {view?.findViewById<ImageView>(R.id.coracao_preenchido)}

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