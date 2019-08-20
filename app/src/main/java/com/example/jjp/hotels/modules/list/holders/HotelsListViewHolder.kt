package com.example.jjp.hotels.modules.list.holders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.jjp.hotels.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_item_hotels_list.hotelAddress
import kotlinx.android.synthetic.main.adapter_item_hotels_list.hotelsContainer
import kotlinx.android.synthetic.main.adapter_item_hotels_list.hotelName
import kotlinx.android.synthetic.main.adapter_item_hotels_list.hotelStars
import kotlinx.android.synthetic.main.adapter_item_hotels_list.hotelSuits

class HotelsListViewHolder
constructor(
    override val containerView: View,
    onItemSelected: (adapterPosition: Int) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private val suitsAvailable = itemView.context.getString(R.string.suits_available)

    init {
        hotelsContainer.setOnClickListener {
            onItemSelected(adapterPosition)
        }
    }

    fun setName(name: String) {
        hotelName.text = name
    }

    fun setStars(stars: Float) {
        hotelStars.rating = stars
    }

    fun setAddress(address: String) {
        hotelAddress.text = address
    }

    fun setSuitsAvailable(suitsCount: String) {
        hotelSuits.text = String.format(suitsAvailable, suitsCount)
    }
}