package com.shivam.diagnalsample.model

import androidx.recyclerview.widget.DiffUtil

data class ContentItemModel(
    val name: String,
    val posterImage: String,
) {
    var posterImageDrawableRes: Int = 0

    object ContentItemDiffUtil : DiffUtil.ItemCallback<ContentItemModel>() {
        override fun areItemsTheSame(
            oldItem: ContentItemModel,
            newItem: ContentItemModel
        ): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(
            oldItem: ContentItemModel,
            newItem: ContentItemModel
        ): Boolean =
            oldItem == newItem
    }
}
