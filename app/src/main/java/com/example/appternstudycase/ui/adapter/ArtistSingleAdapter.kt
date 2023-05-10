package com.example.appternstudycase.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appternstudycase.data.model.albums_model.Data
import com.example.appternstudycase.databinding.CardviewAlbumBinding
import com.squareup.picasso.Picasso

class ArtistSingleAdapter(private val context : Context) : RecyclerView.Adapter<ArtistSingleAdapter.ItemViewHolder>() {

    private var items = emptyList<Data?>()

    inner class ItemViewHolder(private val binding : CardviewAlbumBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(item : Data?)
        {
            binding.apply{
                Picasso.get()
                    .load(item?.cover)
                    .into(imgAlbumtPic)

                txtAlbumName.text = item?.title
                txtPdate.text = item?.release_date
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int, ): ArtistSingleAdapter.ItemViewHolder {
        val binding = CardviewAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtistSingleAdapter.ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateItems(newItems : List<Data?>)
    {
        items = newItems
        notifyDataSetChanged()
    }


}