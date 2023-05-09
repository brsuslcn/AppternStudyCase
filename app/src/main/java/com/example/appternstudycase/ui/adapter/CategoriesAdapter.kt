package com.example.appternstudycase.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.appternstudycase.data.model.categories_model.Data
import com.example.appternstudycase.databinding.CardviewCategoriesBinding
import com.squareup.picasso.Picasso

class CategoriesAdapter() : RecyclerView.Adapter<CategoriesAdapter.ItemViewHolder>() {

    private var items = emptyList<Data?>()

    inner class ItemViewHolder(private val binding: CardviewCategoriesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Data?) {
            binding.categoryName.text = item!!.name
            Picasso.get()
                .load(item!!.picture_medium)
                .into(binding.categoryPic)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = CardviewCategoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateItems(newItems: List<Data?>) {
        items = newItems
        notifyDataSetChanged()
    }
}



