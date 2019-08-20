package com.example.jjp.hotels.data

import com.google.gson.annotations.SerializedName

data class HotelPreview
constructor(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("address") val address: String,
    @SerializedName("stars") val stars: Double,
    @SerializedName("distance") val distance: Double,
    @SerializedName("suites_availability") val suitesAvailable: String
)