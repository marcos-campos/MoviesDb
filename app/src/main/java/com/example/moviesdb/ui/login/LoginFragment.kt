package com.example.moviesdb.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.moviesdb.MainActivity
import com.example.moviesdb.R
import com.example.moviesdb.ui.list.ListMoviesActivity
import com.example.moviesdb.ui.main.MainViewModel


class LoginFragment: Fragment() {

    val btnLogin by lazy { view?.findViewById<Button>(R.id.btn_login) }

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnLogin?.setOnClickListener {
            val intent = Intent(activity, ListMoviesActivity::class.java)
            startActivity(intent)
        }

    }

}