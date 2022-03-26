package com.phongbm.itemdecoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView

/**
 * Create by PhongBM on 03/13/2022
 */

class ListDividerItemDecoration
private constructor(
    context: Context,
    private val includeHead: Boolean,
    private val includeTail: Boolean,
    private val marginStart: Int?,
    private val marginEnd: Int?
) : RecyclerView.ItemDecoration() {
    companion object {
        fun both(context: Context, marginStart: Int? = null, marginEnd: Int? = null) =
            ListDividerItemDecoration(
                context, includeHead = true, includeTail = true, marginStart, marginEnd
            )

        fun center(context: Context, marginStart: Int? = null, marginEnd: Int? = null) =
            ListDividerItemDecoration(
                context, includeHead = false, includeTail = false, marginStart, marginEnd
            )

        fun start(context: Context, marginStart: Int? = null, marginEnd: Int? = null) =
            ListDividerItemDecoration(
                context, includeHead = true, includeTail = false, marginStart, marginEnd
            )

        fun end(context: Context, marginStart: Int? = null, marginEnd: Int? = null) =
            ListDividerItemDecoration(
                context, includeHead = false, includeTail = true, marginStart, marginEnd
            )
    }

    private val divider: Drawable

    init {
        val a = context.obtainStyledAttributes(intArrayOf(android.R.attr.listDivider))
        divider = a.getDrawable(0)!!
        a.recycle()
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val layoutManager = parent.layoutManager ?: return
        val lastPosition = layoutManager.itemCount - 1

        parent.children.forEach { child ->
            val position = parent.getChildAdapterPosition(child)
            if (position == RecyclerView.NO_POSITION) return

            val left = child.left + (marginStart ?: 0)
            val right = child.right - (marginEnd ?: 0)

            if (position == 0 && includeHead) {
                val top = child.top - divider.intrinsicHeight
                val bottom = child.top
                divider.setBounds(left, top, right, bottom)
                divider.draw(c)
            }
            if (position != lastPosition || includeTail) {
                val top = child.bottom
                val bottom = child.bottom + divider.intrinsicHeight
                divider.setBounds(left, top, right, bottom)
                divider.draw(c)
            }
        }
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

        outRect.apply {
            left = 0
            right = 0
            top = if (position == 0 && includeHead) divider.intrinsicHeight else 0
            bottom = if (position == lastPosition && !includeTail) 0 else divider.intrinsicHeight
        }
    }

}