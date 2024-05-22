package com.shivam.diagnalsample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shivam.diagnalsample.R
import com.shivam.diagnalsample.databinding.ItemGridListingBinding
import com.shivam.diagnalsample.model.ContentModel

class DiagnalListingAdapter(val onGridItemClicked: () -> Unit) :
    ListAdapter<ContentModel, DiagnalListingGridItemViewHolder>(ContentModel.ContentItemDiffUtil) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DiagnalListingGridItemViewHolder {
        val binding =
            ItemGridListingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DiagnalListingGridItemViewHolder(binding, onGridItemClicked)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: DiagnalListingGridItemViewHolder, position: Int) {
        holder.onBind(item = getItem(position))
    }
}

class DiagnalListingGridItemViewHolder(
    val binding: ItemGridListingBinding,
    val onGridItemClicked: () -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: ContentModel) {
        binding.ivPoster.setImageDrawable(
            ContextCompat.getDrawable(
                binding.root.context,
                R.drawable.poster2
            )
        )
        binding.tvTitle.text = item.name

        binding.clRoot.setOnClickListener {
            onGridItemClicked()
        }
    }
}