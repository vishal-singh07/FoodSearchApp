package com.example.foodsearchapp.model

import com.google.gson.annotations.SerializedName

data class MenuApiResponse(
    @SerializedName("menus" )
    var menus : ArrayList<Menu> = arrayListOf()
)
