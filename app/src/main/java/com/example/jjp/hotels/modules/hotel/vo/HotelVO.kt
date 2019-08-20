package com.example.jjp.hotels.modules.hotel.vo

import android.graphics.Bitmap

data class HotelVO
constructor(
    val id: Long,
    val name: String,
    val address: String,
    val stars: Float,
    val distance: String,
    val suitesAvailable: String,
    val image: Bitmap?
)