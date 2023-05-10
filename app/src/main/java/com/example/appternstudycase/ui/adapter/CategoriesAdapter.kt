package com.example.appternstudycase.ui.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.appternstudycase.data.model.categories_model.Data
import com.example.appternstudycase.databinding.CardviewCategoriesBinding
import com.example.appternstudycase.ui.activity.CategorySingle
import com.squareup.picasso.Picasso

class CategoriesAdapter(private val context : Context) : RecyclerView.Adapter<CategoriesAdapter.ItemViewHolder>() {

    private var items = emptyList<Data?>()

    inner class ItemViewHolder(private val binding: CardviewCategoriesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Data?) {
            binding.apply {
                txtCategoryName.text = item?.name

                Picasso.get()
                    .load(item?.picture_medium)
                    .into(imgCategoryPic)

                root.setOnClickListener()
                {
                    for(i in items)
                    {
                        if(i?.name == txtCategoryName.text)
                        {
                            val intent = Intent(context, CategorySingle::class.java)
                            intent.putExtra("genreId",i?.id)
                            intent.putExtra("slctdCategory", i?.name)
                            context.startActivity(intent)
                        }
                    }
                }
            }

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



