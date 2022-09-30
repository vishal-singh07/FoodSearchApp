package com.example.foodsearchapp.model

import com.google.gson.annotations.SerializedName

data class OperatingHours(
    @SerializedName("MondayHours"    ) var mondayHours    : String? = null,
    @SerializedName("TuesdayHours"   ) var tuesdayHours   : String? = null,
    @SerializedName("WednesdayHours" ) var wednesdayHours : String? = null,
    @SerializedName("ThursdayHours"  ) var thursdayHours  : String? = null,
    @SerializedName("FridayHours"    ) var fridayHours    : String? = null,
    @SerializedName("SaturdayHours"  ) var saturdayHours  : String? = null,
    @SerializedName("SundayHours"    ) var sundayHours    : String? = null
)
