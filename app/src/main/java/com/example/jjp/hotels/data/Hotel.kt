package com.example.jjp.hotels.data

import com.google.gson.annotations.SerializedName

data class Hotel
constructor(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("address") val address: String,
    @SerializedName("stars") val stars: Double,
    @SerializedName("distance") val distance: Double,
    @SerializedName("suites_availability") val suitesAvailable: String,
    @SerializedName("image") val image: String?,
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val lon: Double
)
