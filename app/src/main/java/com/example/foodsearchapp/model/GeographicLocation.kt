package com.example.foodsearchapp.model

import com.google.gson.annotations.SerializedName

data class GeographicLocation(
    @SerializedName("lat" )
    var lat : Double? = null,
    @SerializedName("lng" )
    var lng : Double? = null
)
