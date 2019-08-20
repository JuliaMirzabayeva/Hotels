package com.example.jjp.hotels.models.list

import com.example.jjp.hotels.data.HotelPreview
import com.example.jjp.hotels.models.listeners.ModelLoadingListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HotelsListRepository
constructor(
    private val hotelsListLoader: HotelsListLoader
) {
    fun getHotels(modelLoadingListener: ModelLoadingListener<List<HotelPreview>>) {
        hotelsListLoader.getHotels(object : Callback<List<HotelPreview>>{
            override fun onResponse(call: Call<List<HotelPreview>>, response: Response<List<HotelPreview>>) {
                modelLoadingListener.onModelLoaded(response.body()!!)
            }

            override fun onFailure(call: Call<List<HotelPreview>>, t: Throwable) {
                modelLoadingListener.onModelFailure(t)
            }
        })
    }
}
