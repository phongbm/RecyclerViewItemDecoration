package com.phongbm.itemdecoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Create by PhongBM on 03/11/2022
 */

class LinearSpacingItemDecoration
private constructor(
    @RecyclerView.Orientation
    private val orientation: Int,
    private val spacing: Int,
    private val includeHead: Boolean,
    private val includeTail: Boolean,
    private val hasEdge: Boolean,
    private val edgeSpacing: Int?
) : RecyclerView.ItemDecoration() {
    companion object {
        private fun verticalCore(
            spacing: Int,
            includeHead: Boolean,
            includeTail: Boolean,
            hasEdge: Boolean,
            edgeSpacing: Int?
        ) = LinearSpacingItemDecoration(
            RecyclerView.VERTICAL, spacing, includeHead, includeTail, hasEdge, edgeSpacing
        )

        fun verticalBoth(spacing: Int, hasEdge: Boolean = false, edgeSpacing: Int? = null) =
            verticalCore(spacing, includeHead = true, includeTail = true, hasEdge, edgeSpacing)

        fun verticalCenter(spacing: Int, hasEdge: Boolean = false, edgeSpacing: Int? = null) =
            verticalCore(spacing, includeHead = false, includeTail = false, hasEdge, edgeSpacing)

        fun verticalStart(spacing: Int, hasEdge: Boolean = false, edgeSpacing: Int? = null) =
            verticalCore(spacing, includeHead = true, includeTail = false, hasEdge, edgeSpacing)

        fun verticalEnd(spacing: Int, hasEdge: Boolean = false, edgeSpacing: Int? = null) =
            verticalCore(spacing, includeHead = false, includeTail = true, hasEdge, edgeSpacing)

        private fun horizontalCore(
            spacing: Int,
            includeHead: Boolean,
            includeTail: Boolean,
            hasEdge: Boolean,
            edgeSpacing: Int?
        ) = LinearSpacingItemDecoration(
            RecyclerView.HORIZONTAL, spacing, includeHead, includeTail, hasEdge, edgeSpacing
        )

        fun horizontalBoth(spacing: Int, hasEdge: Boolean = false, edgeSpacing: Int? = null) =
            horizontalCore(spacing, includeHead = true, includeTail = true, hasEdge, edgeSpacing)

        fun horizontalCenter(spacing: Int, hasEdge: Boolean = false, edgeSpacing: Int? = null) =
            horizontalCore(spacing, includeHead = false, includeTail = false, hasEdge, edgeSpacing)

        fun horizontalStart(spacing: Int, hasEdge: Boolean = false, edgeSpacing: Int? = null) =
            horizontalCore(spacing, includeHead = true, includeTail = false, hasEdge, edgeSpacing)

        fun horizontalEnd(spacing: Int, hasEdge: Boolean = false, edgeSpacing: Int? = null) =
            horizontalCore(spacing, includeHead = false, includeTail = true, hasEdge, edgeSpacing)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (position == RecyclerView.NO_POSITION) return

        val layoutManager = parent.layoutManager ?: return
        val lastPosition = layoutManager.itemCount - 1

        when (orientation) {
            RecyclerView.VERTICAL -> {
                outRect.apply {
                    left = if (hasEdge) edgeSpacing ?: spacing else 0
                    right = if (hasEdge) edgeSpacing ?: spacing else 0
                    top = if (position == 0 && includeHead) spacing else 0
                    bottom = if (position == lastPosition && !includeTail) 0 else spacing
                }
            }
            RecyclerView.HORIZONTAL -> {
                outRect.apply {
                    top = if (hasEdge) edgeSpacing ?: spacing else 0
                    bottom = if (hasEdge) edgeSpacing ?: spacing else 0
                    left = if (position == 0 && includeHead) spacing else 0
                    right = if (position == lastPosition && !includeTail) 0 else spacing
                }
            }
        }
    }

}