package com.example.moviesdb.ui.main.model

import com.google.gson.annotations.SerializedName

data class GenresResponse (

        @SerializedName("genres")
        val listaGeneros: ArrayList<Genre>
)