package com.example.jjp.hotels.api

import com.example.jjp.hotels.data.HotelPreview
import com.example.jjp.hotels.data.Hotel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("0777.json")
    fun getHotels(): Call<List<HotelPreview>>

    @GET("{hotelId}.json")
    fun getHotel(@Path("hotelId") hotelId: Long): Call<Hotel>

    @GET("{imageId}")
    fun getHotelImage(@Path("imageId") imageId: String): Call<ResponseBody>
}