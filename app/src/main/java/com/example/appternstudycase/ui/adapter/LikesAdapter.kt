package com.example.appternstudycase.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.appternstudycase.R
import com.example.appternstudycase.data.model.sql_likes_model.TracksLikesModel
import com.example.appternstudycase.databinding.CardviewTracksBinding
import com.example.appternstudycase.ui.viewmodel.LikesViewModel
import com.squareup.picasso.Picasso

class LikesAdapter(private val likeListener: LikeListener, private val lifecycleOwner: LifecycleOwner) : RecyclerView.Adapter<LikesAdapter.ItemViewHolder>() {

    private var items = emptyList<TracksLikesModel>()

    inner class ItemViewHolder(private val binding : CardviewTracksBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(item : TracksLikesModel?)
        {
            binding.apply {
                Picasso.get()
                    .load(item?.track_picture)
                    .into(imgTracksPic)

            txtTrackName.text = item?.track_title
            txtDuration.text = item?.track_duration

            btnLike.setImageResource(R.drawable.heart_icon_fill)

            btnLike.setOnClickListener(){
                val track = TracksLikesModel(item!!.track_id, item!!.track_title, item!!.track_duration, item.track_picture)
                likeListener.dislikeTrack(track)
                removeItem(adapterPosition)


                //Log.e("Güncel Likes : ", likeListener.getLikes().value.toString())
                likeListener.getLikes().observe(lifecycleOwner, Observer { deneme ->
                    Log.e("likeler", deneme.toString())
                })

                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikesAdapter.ItemViewHolder {
        val binding = CardviewTracksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LikesAdapter.ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateItems(newItem : List<TracksLikesModel>)
    {
        items=newItem
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        val currentList = items.toMutableList()
        currentList.removeAt(position)
        updateItems(currentList)
    }

}

