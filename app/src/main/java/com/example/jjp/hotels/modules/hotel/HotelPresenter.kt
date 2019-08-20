package com.example.jjp.hotels.modules.hotel

import android.graphics.Bitmap
import com.example.jjp.hotels.data.Hotel
import com.example.jjp.hotels.models.hotel.HotelProvider
import com.example.jjp.hotels.models.list.HotelsListProvider
import com.example.jjp.hotels.modules.hotel.mappers.HotelMapper
import nucleus.presenter.Presenter
import javax.inject.Inject
import android.content.Intent
import android.net.Uri

class HotelPresenter
@Inject constructor(
    private val hotelProvider: HotelProvider,
    private val hotelsListProvider: HotelsListProvider,
    private val hotelMapper: HotelMapper
): Presenter<HotelView>() {

    private val hotelLoadedListener = object : HotelProvider.HotelLoadedListener {
        override fun onHotelLoaded(hotel: Hotel, image: Bitmap?) {
            view?.hideProgress()
            view?.setHotel(hotelMapper.mapHotel(hotel, image))
        }

        override fun onHotelFailed(error: String?) {
            view?.hideProgress()
            view?.showError(error)
        }
    }

    override fun onTakeView(view: HotelView?) {
        super.onTakeView(view)
        hotelProvider.addHotelLoadedListener(hotelLoadedListener)

        loadHotel(hotelsListProvider.currentHotelId!!)
    }

    override fun dropView() {
        hotelProvider.removeHotelLoadedListener(hotelLoadedListener)
        super.dropView()
    }

    private fun loadHotel(hotelId: Long) {
        hotelProvider.loadHotel(hotelId)
    }

    fun getHotelOnMapIntent(): Intent {
        return Intent(
            Intent.ACTION_VIEW,
            Uri.parse(getHotelCoordinates())
        )
    }

    private fun getHotelCoordinates(): String {
        return String.format(
            MAP_COORDINATES,
            hotelProvider.currentHotel.lat,
            hotelProvider.currentHotel.lon
        )
    }

    companion object {
        private const val MAP_COORDINATES = "geo:%s,%s"
    }
}