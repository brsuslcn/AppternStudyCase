package com.example.appternstudycase.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appternstudycase.data.model.arists_model.Data
import com.example.appternstudycase.databinding.CardviewArtistsBinding
import com.example.appternstudycase.ui.activity.ArtistSingle
import com.squareup.picasso.Picasso

class ArtistsAdapter(private val context : Context) : RecyclerView.Adapter<ArtistsAdapter.ItemViewHolder>() {

    private var items = emptyList<Data?>()

    inner class ItemViewHolder(private val binding : CardviewArtistsBinding) : RecyclerView.ViewHolder(binding.root)
    {

        fun bind(item : Data?)
        {
            binding.apply{
                txtArtistName.text = item?.name

                Picasso.get()
                    .load(item?.picture_medium)
                    .into(imgArtistPic)

                root.setOnClickListener()
                {
                    val intent = Intent(context, ArtistSingle::class.java)
                    intent.putExtra("artistName", item?.name)
                    intent.putExtra("artistPicture", item?.picture_big)
                    intent.putExtra("artistId",item?.id)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent)

                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int, ): ArtistsAdapter.ItemViewHolder {
        val binding = CardviewArtistsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtistsAdapter.ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateItems(newItems : List<Data?>)
    {
        items=newItems
        notifyDataSetChanged()
    }

}