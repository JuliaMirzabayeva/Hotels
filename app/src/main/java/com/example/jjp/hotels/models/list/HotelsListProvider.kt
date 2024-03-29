package com.example.jjp.hotels.models.list

import android.annotation.SuppressLint
import com.example.jjp.hotels.api.ApiService
import com.example.jjp.hotels.data.HotelPreview
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HotelsListProvider
constructor(
    private val api: ApiService
) {
    var currentHotelId: Long? = null

    private var hotelsPreview = listOf<HotelPreview>()
    private val hotelsPreviewLoadedListeners = hashSetOf<HotelsPreviewLoadedListener>()

    interface HotelsPreviewLoadedListener {
        fun onHotelsLoaded(hotelsPreview: List<HotelPreview>)
        fun onHotelsFailed(error: String?)
    }

    @SuppressLint("CheckResult")
    fun loadHotels() {
        api.getHotels()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onHotelsLoaded, ::onHotelsFailed)
    }

    private fun onHotelsLoaded(hotels: List<HotelPreview>) {
        hotelsPreview = hotels
        notifyHotelsLoaded(hotels)
    }

    private fun onHotelsFailed(throwable: Throwable) {
        notifyHotelsFailed(throwable.message)
    }

    private fun notifyHotelsLoaded(hotelsPreview: List<HotelPreview>) {
        hotelsPreviewLoadedListeners.forEach { listener ->
            listener.onHotelsLoaded(hotelsPreview)
        }
    }

    private fun notifyHotelsFailed(error: String? = null) {
        hotelsPreviewLoadedListeners.forEach { listener ->
            listener.onHotelsFailed(error)
        }
    }

    fun addHotelsPreviewLoadedListener(listener: HotelsPreviewLoadedListener) {
        hotelsPreviewLoadedListeners.add(listener)
    }

    fun removeHotelsPreviewLoadedListener(listener: HotelsPreviewLoadedListener) {
        hotelsPreviewLoadedListeners.remove(listener)
    }

    fun getSortedHotels(sortType: SortType): List<HotelPreview> {
        return when(sortType) {
            SortType.DEFAULT -> hotelsPreview
            SortType.DISTANCE -> getHotelsSortedByDistance()
            SortType.SUITS -> getHotelsSortedBySuits()
        }
    }

    private fun getHotelsSortedByDistance(): List<HotelPreview> {
        return hotelsPreview.sortedBy { hotel -> hotel.distance }
    }

    private fun getHotelsSortedBySuits(): List<HotelPreview> {
        return hotelsPreview.sortedByDescending { hotel -> getAvailableSuitsCount(hotel.suitesAvailable) }
    }

    private fun getAvailableSuitsCount(suitesAvailable: String): Int {
        return if (suitesAvailable.isEmpty()) {
            NO_SUITS_AVAILABLE
        } else {
            val suits = suitesAvailable.split(SUITS_AVAILABLE_SPLIT)
            suits.size
        }
    }

    enum class SortType {
        DEFAULT,
        DISTANCE,
        SUITS
    }

    companion object {
        private const val SUITS_AVAILABLE_SPLIT = ":"
        private const val NO_SUITS_AVAILABLE = 0
    }
}