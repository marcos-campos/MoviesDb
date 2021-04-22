package com.example.moviesdb.model

import com.google.gson.annotations.SerializedName

data class MovieSimilar(

        @SerializedName("page")
    val page: Int?,

        @SerializedName("results")
    val results: List<MovieSimilarResponse>?,

        @SerializedName("total_pages")
    val totalPages: Int?,

        @SerializedName("total_results")
    val totalResults: Int?
)