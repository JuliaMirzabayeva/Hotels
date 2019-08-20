package com.example.jjp.hotels.modules.hotel.mappers

import android.graphics.Bitmap
import com.example.jjp.hotels.R
import com.example.jjp.hotels.data.Hotel
import com.example.jjp.hotels.modules.hotel.vo.HotelVO
import javax.inject.Inject

class HotelMapper
@Inject constructor() {

    fun mapHotel(hotel: Hotel, hotelImage: Bitmap?): HotelVO {
        return HotelVO(
            id = hotel.id,
            name = hotel.name,
            address = hotel.address,
            stars = hotel.stars.toFloat(),
            distance = hotel.distance.toString(),
            suitesAvailable = getAvailableSuitsCount(hotel.suitesAvailable).toString(),
            image = getHotelImage(hotelImage)
        )
    }

    private fun getAvailableSuitsCount(suitesAvailable: String): Int {
        return if (suitesAvailable.isEmpty()) {
            NO_SUITS_AVAILABLE
        } else {
            val suits = suitesAvailable.split(SUITS_AVAILABLE_SPLIT)
            suits.size
        }
    }

    private fun getHotelImage(hotelImage: Bitmap?): Bitmap? {
        return hotelImage?.let { image ->
            getCroppedImage(image)
        }
    }

    private fun getCroppedImage(hotelImage: Bitmap): Bitmap {
        return Bitmap.createBitmap(
            hotelImage,
            IMAGE_BORDER_SIZE,
            IMAGE_BORDER_SIZE,
            hotelImage.width - IMAGE_BORDER_SIZE * 2,
            hotelImage.height - IMAGE_BORDER_SIZE * 2
        )
    }

    companion object {
        private const val SUITS_AVAILABLE_SPLIT = ":"
        private const val NO_SUITS_AVAILABLE = 0
        private const val IMAGE_BORDER_SIZE = 1
    }
}