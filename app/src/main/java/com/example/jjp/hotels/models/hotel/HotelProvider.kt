package com.example.jjp.hotels.models.hotel

import android.graphics.Bitmap
import com.example.jjp.hotels.models.listeners.ModelLoadingListener
import com.example.jjp.hotels.data.Hotel

class HotelProvider
constructor(
    private val hotelRepository: HotelRepository
) {

    lateinit var currentHotel: Hotel
    private val hotelLoadedListeners = hashSetOf<HotelLoadedListener>()

    interface HotelLoadedListener {
        fun onHotelLoaded(hotel: Hotel, image: Bitmap?)
        fun onHotelFailed(error: String?)
    }

    fun loadHotel(hotelId: Long) {
        hotelRepository.getHotel(hotelId, object : ModelLoadingListener<Hotel> {
            override fun onModelLoaded(model: Hotel) {
                currentHotel = model

                model.image?.let { image ->
                    loadHotelImage(image)
                } ?: notifyHotelLoaded(currentHotel, null)
            }

            override fun onModelError(error: String?) {
                notifyHotelsFailed(error)
            }

            override fun onModelFailure(error: Throwable?) {
                notifyHotelsFailed()
            }
        })
    }

    private fun loadHotelImage(imageId: String) {
        hotelRepository.getHotelImage(imageId, object : ModelLoadingListener<Bitmap?> {
            override fun onModelLoaded(model: Bitmap?) {
                notifyHotelLoaded(currentHotel, model)
            }

            override fun onModelError(error: String?) {
                notifyHotelsFailed(error)
            }

            override fun onModelFailure(error: Throwable?) {
                notifyHotelsFailed()
            }
        })
    }

    private fun notifyHotelLoaded(hotel: Hotel, image: Bitmap?) {
        hotelLoadedListeners.forEach { listener ->
            listener.onHotelLoaded(hotel, image)
        }
    }

    private fun notifyHotelsFailed(error: String? = null) {
        hotelLoadedListeners.forEach { listener ->
            listener.onHotelFailed(error)
        }
    }

    fun addHotelLoadedListener(listener: HotelLoadedListener) {
        hotelLoadedListeners.add(listener)
    }

    fun removeHotelLoadedListener(listener: HotelLoadedListener) {
        hotelLoadedListeners.remove(listener)
    }
}