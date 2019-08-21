package com.example.jjp.hotels.modules.hotel

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jjp.hotels.R
import com.example.jjp.hotels.dagger.App
import com.example.jjp.hotels.modules.BaseFragment
import com.example.jjp.hotels.modules.hotel.vo.HotelVO
import kotlinx.android.synthetic.main.fragment_hotel.*
import android.view.Menu

class HotelFragment : BaseFragment<HotelPresenter>(), HotelView {

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setHasOptionsMenu(true)

        setPresenterFactory {
            App.component().provideHotelPresenter()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hotel, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.clear()
    }

    private fun setClickListeners() {
        hotelAddress.setOnClickListener {
            showHotelOnMap()
        }
    }

    private fun showHotelOnMap() {
        val intent = presenter?.getHotelOnMapIntent()
        requireContext().startActivity(intent)
    }

    override fun hideProgress() {
        hotelProgress.visibility = View.GONE
    }

    override fun setHotel(hotel: HotelVO) {
        setHotelName(hotel.name)
        setHotelAddress(hotel.address)
        setHotelStars(hotel.stars)
        setSuitsAvailable(hotel.suitesAvailable)

        hotel.image?.let { image ->
            setHotelImage(image)
        }
        setNoImageVisibility(hotel.image == null)
    }

    private fun setHotelName(name: String) {
        hotelName.text = name
    }

    private fun setHotelAddress(address: String) {
        hotelAddress.text = address
    }

    private fun setHotelStars(stars: Float) {
        hotelStars.rating = stars
    }

    private fun setHotelImage(image: Bitmap) {
        hotelImage.setImageBitmap(image)
    }

    private fun setSuitsAvailable(suitsCount: String) {
        hotelSuits.text = String.format(
            requireContext().getString(R.string.suits_available),
            suitsCount
        )
    }

    private fun setNoImageVisibility(isVisible: Boolean) {
        hotelNoImage.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    companion object {
        const val TAG = "hotel_fragment"
    }
}