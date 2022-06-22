package com.example.catapp.presenter.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.abstractions.CatPhoto
import com.example.catapp.databinding.ItemCatRecyclerViewBinding

class CatItemAdapter : ListAdapter<com.example.abstractions.CatPhoto, CatItemAdapter.CatItemViewHolder>(DIFF_CALLBACK) {
    var onClickListener: ((_cat: com.example.abstractions.CatPhoto) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatItemViewHolder {
        val binding =
            ItemCatRecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        return CatItemViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: CatItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CatItemViewHolder(
        private val binding: ItemCatRecyclerViewBinding,
        private val onClickListener: ((position: com.example.abstractions.CatPhoto) -> Unit)?,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cat: com.example.abstractions.CatPhoto) {
            try {
                binding.imageCat.setImageBitmap(cat.image)
                binding.root.setOnClickListener {
                    onClickListener?.invoke(cat)
                }
            } catch (e: Exception) {
                Log.i("Erro: ", e.toString())
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<com.example.abstractions.CatPhoto>() {
            override fun areItemsTheSame(oldItem: com.example.abstractions.CatPhoto, newItem: com.example.abstractions.CatPhoto): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: com.example.abstractions.CatPhoto, newItem: com.example.abstractions.CatPhoto): Boolean {
                return oldItem == newItem
            }
        }
    }
}
