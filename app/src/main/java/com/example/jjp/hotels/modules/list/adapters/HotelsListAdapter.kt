package com.example.jjp.hotels.modules.list.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.jjp.hotels.R
import com.example.jjp.hotels.modules.list.binders.HotelsListBinder
import com.example.jjp.hotels.modules.list.holders.HotelsListViewHolder
import com.example.jjp.hotels.modules.list.vo.HotelsListVO

class HotelsListAdapter
constructor(
    private val onHotelSelected: (hotelId: Long) -> Unit
) : RecyclerView.Adapter<HotelsListViewHolder>() {

    private val items = mutableListOf<HotelsListVO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelsListViewHolder {
        return HotelsListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.adapter_item_hotels_list,
                parent,
                false
            ),
            ::selectHotel
        )
    }

    private fun selectHotel(position: Int) {
        onHotelSelected(items[position].id)
    }

    override fun onBindViewHolder(holder: HotelsListViewHolder, position: Int) {
        HotelsListBinder.bind(items[position], holder)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setHotels(hotelsList: List<HotelsListVO>) {
        items.clear()
        items.addAll(hotelsList)
        notifyDataSetChanged()
    }
}