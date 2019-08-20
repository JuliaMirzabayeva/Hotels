package com.example.jjp.hotels.modules.list.vo

data class HotelsListVO
constructor(
    val id: Long,
    val name: String,
    val address: String,
    val stars: Float,
    val distance: String,
    val suitesAvailable: String
)