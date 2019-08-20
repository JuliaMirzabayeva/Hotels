package com.example.jjp.hotels.modules.hotel

import com.example.jjp.hotels.modules.hotel.vo.HotelVO

interface HotelView {

    fun hideProgress()

    fun setHotel(hotel: HotelVO)

    fun showError(error: String?)
}