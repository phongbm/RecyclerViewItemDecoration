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
    private val marginStart: Int?
) : RecyclerView.ItemDecoration() {
    companion object {
        fun both(context: Context, marginStart: Int? = null) =
            ListDividerItemDecoration(context, includeHead = true, includeTail = true, marginStart)

        fun center(context: Context, marginStart: Int? = null) =
            ListDividerItemDecoration(context, includeHead = false, includeTail = false, marginStart)

        fun start(context: Context, marginStart: Int? = null) =
            ListDividerItemDecoration(context, includeHead = true, includeTail = false, marginStart)

        fun end(context: Context, marginStart: Int? = null) =
            ListDividerItemDecoration(context, includeHead = false, includeTail = true, marginStart)
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
            val right = child.right

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

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
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