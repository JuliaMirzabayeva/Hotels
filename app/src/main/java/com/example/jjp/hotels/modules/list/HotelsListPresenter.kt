package com.example.jjp.hotels.modules.list

import com.example.jjp.hotels.data.HotelPreview
import com.example.jjp.hotels.models.list.HotelsListProvider
import com.example.jjp.hotels.modules.list.mappers.HotelsListMapper
import com.example.jjp.hotels.modules.list.vo.HotelsListVO
import nucleus.presenter.Presenter
import javax.inject.Inject

class HotelsListPresenter
@Inject constructor(
    private val hotelsListProvider: HotelsListProvider,
    private val hotelsListMapper: HotelsListMapper
) : Presenter<HotelListView>() {

    private var currentSortType = HotelsListProvider.SortType.DEFAULT

    private val hotelsPreviewLoadedListener = object : HotelsListProvider.HotelsPreviewLoadedListener {
        override fun onHotelsLoaded(hotelsPreview: List<HotelPreview>) {
            hideProgress()
            view?.setHotels(getSortedHotels(currentSortType))
        }

        override fun onHotelsFailed(error: String?) {
            hideProgress()
            view?.showError(error)
        }
    }

    override fun onTakeView(view: HotelListView?) {
        super.onTakeView(view)
        hotelsListProvider.addHotelsPreviewLoadedListener(hotelsPreviewLoadedListener)

        loadHotels()
    }

    override fun onDropView() {
        hotelsListProvider.removeHotelsPreviewLoadedListener(hotelsPreviewLoadedListener)
        super.onDropView()
    }

    private fun hideProgress() {
        view?.setProgressVisibility(false)
    }

    fun loadHotels() {
        view?.setProgressVisibility(true)
        hotelsListProvider.loadHotels()
    }

    private fun getSortedHotels(sortType: HotelsListProvider.SortType): List<HotelsListVO> {
        return hotelsListProvider.getSortedHotels(sortType)
            .map { hotel -> hotelsListMapper.mapHotelsResponse(hotel) }
    }

    fun sortHotels(sortType: HotelsListProvider.SortType) {
        if (currentSortType != sortType) {
            view?.setHotels(getSortedHotels(sortType))
            currentSortType = sortType
        }
    }

    fun setCurrentHotel(hotelId: Long) {
        hotelsListProvider.currentHotelId = hotelId
    }
}
