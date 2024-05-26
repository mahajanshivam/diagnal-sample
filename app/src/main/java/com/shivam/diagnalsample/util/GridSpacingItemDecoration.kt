package com.shivam.diagnalsample.util

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.shivam.diagnalsample.R

class GridSpacingItemDecoration(resources: Resources) : RecyclerView.ItemDecoration() {

    var spanCount: Int = 0
    val spacing = 32

    init {
        spanCount = resources.getInteger(R.integer.grid_column_count)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view) // item position
        val column: Int = position % spanCount // item column

        outRect.left =
            spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
        outRect.right =
            (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

        if (position < spanCount) { // top edge
            outRect.top = spacing;
        }
        outRect.bottom = spacing; // item bottom
    }

}