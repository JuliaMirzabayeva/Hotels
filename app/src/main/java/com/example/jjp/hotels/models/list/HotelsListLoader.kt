package com.example.jjp.hotels.models.list

import com.example.jjp.hotels.api.ApiService
import com.example.jjp.hotels.data.HotelPreview
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HotelsListLoader
constructor(
    private val api: ApiService
) {
    fun getHotels(callback: Callback<List<HotelPreview>>) {
        api.getHotels().enqueue(HotelsApiCallback(callback))
    }

    private inner class HotelsApiCallback
    constructor(
        val callback: Callback<List<HotelPreview>>
    ) : Callback<List<HotelPreview>> {
        override fun onFailure(call: Call<List<HotelPreview>>, t: Throwable?) {
            callback.onFailure(call, t)
        }

        override fun onResponse(call: Call<List<HotelPreview>>, response: Response<List<HotelPreview>>?) {
            callback.onResponse(call, response)
        }
    }
}