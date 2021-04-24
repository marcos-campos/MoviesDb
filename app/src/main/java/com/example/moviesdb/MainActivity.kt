package com.example.moviesdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviesdb.modelListaFilmes.Item
import com.example.moviesdb.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val informacoes = intent.extras
        val filme = informacoes?.getSerializable("dadosFilmes") as Item

        if (savedInstanceState == null) {
            filme.id?.let { MainFragment.newInstance(it) }?.let {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.container, it)
                        .commitNow()
            }
        }
    }
}