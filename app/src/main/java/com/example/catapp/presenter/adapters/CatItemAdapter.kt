package com.example.catapp.presenter.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.catapp.databinding.ItemCatRecyclerViewBinding

class CatItemAdapter : ListAdapter<CatPhoto, CatItemAdapter.CatItemViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatItemViewHolder {
        val binding =
            ItemCatRecyclerViewBinding.inflate(
                LayoutInflater
                    .from(parent.context),
                parent, false
            )
        return CatItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CatItemViewHolder(private val binding: ItemCatRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cat: CatPhoto) {
            try {
                binding.imageCat.setImageBitmap(cat.image)
            } catch (e: Exception) {
                Log.i("Erro: ", e.toString())
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CatPhoto>() {
            override fun areItemsTheSame(oldItem: CatPhoto, newItem: CatPhoto): Boolean {
                return oldItem.image == newItem.image
            }

            override fun areContentsTheSame(oldItem: CatPhoto, newItem: CatPhoto): Boolean {
                return oldItem == newItem
            }
        }
    }
}
