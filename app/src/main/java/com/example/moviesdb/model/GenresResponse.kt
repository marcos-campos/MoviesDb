package com.example.moviesdb.model

import com.example.moviesdb.model.Genre
import com.google.gson.annotations.SerializedName

data class GenresResponse (

        @SerializedName("genres")
        val listaGeneros: ArrayList<Genre>
)