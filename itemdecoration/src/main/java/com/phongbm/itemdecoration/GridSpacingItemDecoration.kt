package com.phongbm.itemdecoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Create by PhongBM on 03/13/2022
 */

class GridSpacingItemDecoration
constructor(
    private val spanCount: Int,
    private val spacing: Int,
    private val hasEdge: Boolean = false
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        if (position == RecyclerView.NO_POSITION) return

        val column = position % spanCount

        if (hasEdge) {
            outRect.apply {
                left = spacing - column * spacing / spanCount
                right = (column + 1) * spacing / spanCount
                top = if (position < spanCount) spacing else 0
                bottom = spacing
            }
        } else {
            outRect.apply {
                left = column * spacing / spanCount
                right = spacing - (column + 1) * spacing / spanCount
                top = if (position < spanCount) 0 else spacing
                bottom = 0
            }
        }
    }

}