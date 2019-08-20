package com.example.jjp.hotels.modules.list

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.jjp.hotels.R

class HotelsListItemDecoration
constructor(context: Context) : RecyclerView.ItemDecoration() {

    private val marginS = context.resources.getDimensionPixelOffset(R.dimen.margin_s)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)

        if (position != INCORRECT_POSITION) {
            if (position == 0) {
                outRect.top = marginS
            }
            outRect.bottom = marginS
        }
    }

    companion object {
        private const val INCORRECT_POSITION = -1
    }
}