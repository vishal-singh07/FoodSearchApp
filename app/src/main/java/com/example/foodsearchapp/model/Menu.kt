package com.example.foodsearchapp.model

import com.google.gson.annotations.SerializedName

data class Menu(
    @SerializedName("restaurantId" )
    var restaurantId : Int? = null,
    @SerializedName("categories")
    var categories   : ArrayList<Category> = arrayListOf()
)
