package com.shivam.diagnalsample.model

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

data class PageModel(
    @SerializedName("page") val page: PageContentModel? = null
)

data class PageContentModel(
    @SerializedName("title") val title: String? = null,
    @SerializedName("total-content-items") val totalContentItems: String? = null,
    @SerializedName("page-num") val pageNum: String? = null,
    @SerializedName("page-size") val pageSize: String? = null,
    @SerializedName("content-items") val contentItemModel: ContentItemsModel? = null,
)

data class ContentItemsModel(
    @SerializedName("content") val content: ArrayList<ContentModel?>? = null,
)

data class ContentModel(
    val name: String,
    val posterImage: String,
) {
    var posterImageDrawableRes: Int = 0

    object ContentItemDiffUtil : DiffUtil.ItemCallback<ContentModel>() {
        override fun areItemsTheSame(
            oldItem: ContentModel,
            newItem: ContentModel
        ): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(
            oldItem: ContentModel,
            newItem: ContentModel
        ): Boolean =
            oldItem == newItem
    }
}
