package com.example.jjp.hotels.modules.list.binders

import com.example.jjp.hotels.modules.list.holders.HotelsListViewHolder
import com.example.jjp.hotels.modules.list.vo.HotelsListVO

object HotelsListBinder {

    fun bind(item: HotelsListVO, holder: HotelsListViewHolder) {
        holder.bind(item)
    }

    private fun HotelsListViewHolder.bind(item: HotelsListVO) {
        setName(item.name)
        setStars(item.stars)
        setAddress(item.address)
        setSuitsAvailable(item.suitesAvailable)
    }
}