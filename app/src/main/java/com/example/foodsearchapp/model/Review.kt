package com.example.foodsearchapp.model

import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("name"     ) var name     : String? = null,
    @SerializedName("date"     ) var date     : String? = null,
    @SerializedName("rating"   ) var rating   : Int?    = null,
    @SerializedName("comments" ) var comments : String? = null
)
