package com.example.moviesdb.model

import com.example.moviesdb.model.Item
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ListaFilmes(

        @SerializedName("created_by")
    val createdBy: String?,

        @SerializedName("description")
    val description: String?,

        @SerializedName("favorite_count")
    val favorite_count: Int?,

        @SerializedName("id")
    val id: String?,

        @SerializedName("iso_369_1")
    val iso6391: String?,

        @SerializedName("item_count")
    val itemCount: Int?,

        @SerializedName("items")
    val items: List<Item>?,

        @SerializedName("name")
    val name: String?,

        @SerializedName("poster_path")
    val posterPath: String?

)  : Serializable