package com.example.foodsearchapp.model

import com.google.gson.annotations.SerializedName

data class RestaurantApiResponse(
    @SerializedName("restaurants" )
    var restaurants : ArrayList<Restaurant> = arrayListOf()
)
