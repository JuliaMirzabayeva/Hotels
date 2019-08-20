package com.example.jjp.hotels.models.hotel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.jjp.hotels.models.listeners.ModelLoadingListener
import com.example.jjp.hotels.data.Hotel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HotelRepository
constructor(
    private val hotelLoader: HotelLoader
) {
    fun getHotel(hotelId: Long, modelLoadingListener: ModelLoadingListener<Hotel>) {
        hotelLoader.getHotel(hotelId, object : Callback<Hotel>{
            override fun onResponse(call: Call<Hotel>, response: Response<Hotel>) {
                modelLoadingListener.onModelLoaded(response.body()!!)
            }

            override fun onFailure(call: Call<Hotel>, t: Throwable) {
                modelLoadingListener.onModelFailure(t)
            }
        })
    }

    fun getHotelImage(imageId: String, modelLoadingListener: ModelLoadingListener<Bitmap?>) {
        hotelLoader.getHotelImage(imageId, object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val bitmap = response.body()?.let { responseBody ->
                    BitmapFactory.decodeStream(responseBody.byteStream())
                }
                modelLoadingListener.onModelLoaded(bitmap)
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                modelLoadingListener.onModelFailure(t)
            }
        })
    }
}