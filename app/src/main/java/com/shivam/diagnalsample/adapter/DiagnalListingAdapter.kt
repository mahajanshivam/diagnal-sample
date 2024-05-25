package com.shivam.diagnalsample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shivam.diagnalsample.databinding.ItemGridListingBinding
import com.shivam.diagnalsample.model.ContentModel
import com.shivam.diagnalsample.util.PosterImageMappingUtil

class DiagnalListingAdapter(
    val movieList: ArrayList<ContentModel>,
    val onGridItemClicked: () -> Unit
) :
    RecyclerView.Adapter<DiagnalListingGridItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DiagnalListingGridItemViewHolder {
        val binding =
            ItemGridListingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DiagnalListingGridItemViewHolder(binding, onGridItemClicked)
    }

    override fun onBindViewHolder(holder: DiagnalListingGridItemViewHolder, position: Int) {
        movieList[position].let { holder.onBind(item = it) }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}

class DiagnalListingGridItemViewHolder(
    val binding: ItemGridListingBinding,
    val onGridItemClicked: () -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: ContentModel) {

        PosterImageMappingUtil.getPosterDrawable(context = binding.root.context, item.posterImage)
            ?.let {
                binding.ivPoster.setImageDrawable(it)
            }

//        posterDrawable?.let {
//            binding.ivPoster.setImageDrawable(
////                posterDrawable
//
//
////            ContextCompat.getDrawable(
////                binding.root.context,
////                R.drawable.poster2
////            )
//            )
//        }

        binding.tvTitle.text = item.name

        binding.clRoot.setOnClickListener {
            onGridItemClicked()
        }
    }
}