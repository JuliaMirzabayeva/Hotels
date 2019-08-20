package com.example.jjp.hotels.models.hotel

import com.example.jjp.hotels.api.ApiService
import com.example.jjp.hotels.data.Hotel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HotelLoader
constructor(
    private val api: ApiService
) {
    fun getHotel(hotelId: Long, callback: Callback<Hotel>) {
        api.getHotel(hotelId).enqueue(HotelApiCallback(callback))
    }

    fun getHotelImage(imageId: String, callback: Callback<ResponseBody>) {
        api.getHotelImage(imageId).enqueue(HotelImageApiCallback(callback))
    }

    private inner class HotelApiCallback
    constructor(
        val callback: Callback<Hotel>
    ) : Callback<Hotel> {
        override fun onFailure(call: Call<Hotel>, t: Throwable?) {
            callback.onFailure(call, t)
        }

        override fun onResponse(call: Call<Hotel>, response: Response<Hotel>?) {
            callback.onResponse(call, response)
        }
    }

    private inner class HotelImageApiCallback
    constructor(
        val callback: Callback<ResponseBody>
    ) : Callback<ResponseBody> {
        override fun onFailure(call: Call<ResponseBody>, t: Throwable?) {
            callback.onFailure(call, t)
        }

        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>?) {
            callback.onResponse(call, response)
        }
    }
}