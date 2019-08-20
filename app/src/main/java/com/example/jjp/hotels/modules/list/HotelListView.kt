package com.example.jjp.hotels.modules.list

import com.example.jjp.hotels.modules.list.vo.HotelsListVO

interface HotelListView {

    fun setProgressVisibility(isVisible: Boolean)

    fun setHotels(hotels: List<HotelsListVO>)

    fun showError(error: String?)
}