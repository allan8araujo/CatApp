package com.example.catapp.presenter.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.abstractions.CatPhoto
import com.example.catapp.databinding.FragmentHistoryBinding
import com.example.catapp.databinding.ItemCatRecyclerViewBinding

class CatItemAdapter(val binding_: FragmentHistoryBinding) :
    PagingDataAdapter<CatPhoto, CatItemAdapter.CatItemViewHolder>(DIFF_CALLBACK) {
    var onClickListener: ((_cat: CatPhoto) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatItemViewHolder {
        val binding =
            ItemCatRecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        return CatItemViewHolder(binding, binding_, onClickListener)
    }

    override fun onBindViewHolder(holder: CatItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CatItemViewHolder(
        private val binding: ItemCatRecyclerViewBinding,
        private val bindingHistBinding: FragmentHistoryBinding,
        private val onClickListener: ((position: CatPhoto) -> Unit)?,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cat: CatPhoto?) {
            try {
                binding.imageCat.setImageBitmap(cat?.image)
                binding.root.setOnClickListener {
                    onClickListener?.invoke(cat!!)
                }
                bindingHistBinding.pbLoadingHistory.isVisible = false
            } catch (e: Exception) {
                // unused
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<CatPhoto>() {
                override fun areItemsTheSame(
                    oldItem: CatPhoto,
                    newItem: CatPhoto,
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: CatPhoto,
                    newItem: CatPhoto,
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}
