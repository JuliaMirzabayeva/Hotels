package com.example.jjp.hotels.modules.list.mappers

import com.example.jjp.hotels.data.HotelPreview
import com.example.jjp.hotels.modules.list.vo.HotelsListVO
import javax.inject.Inject

class HotelsListMapper
@Inject constructor() {

    fun mapHotelsResponse(hotelPreview: HotelPreview): HotelsListVO {
        return HotelsListVO(
            id = hotelPreview.id,
            name = hotelPreview.name,
            address = hotelPreview.address,
            stars = hotelPreview.stars.toFloat(),
            distance = hotelPreview.distance.toString(),
            suitesAvailable = getAvailableSuitsCount(hotelPreview.suitesAvailable).toString()
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

    companion object {
        private const val SUITS_AVAILABLE_SPLIT = ":"
        private const val NO_SUITS_AVAILABLE = 0
    }
}