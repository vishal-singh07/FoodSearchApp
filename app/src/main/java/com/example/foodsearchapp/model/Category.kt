package com.example.foodsearchapp.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id")
    var id : String? = null,
    @SerializedName("name")
    var name : String? = null,
    @SerializedName("menuItems" )
    var menuItems : ArrayList<MenuItem> = arrayListOf()
)
