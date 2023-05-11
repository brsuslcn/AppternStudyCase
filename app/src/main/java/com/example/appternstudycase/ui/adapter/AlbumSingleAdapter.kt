package com.example.appternstudycase.ui.adapter

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.appternstudycase.data.model.sql_likes_model.TracksLikesModel
import com.example.appternstudycase.data.model.tracks_model.Data
import com.example.appternstudycase.databinding.CardviewTracksBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.DecimalFormat

class AlbumSingleAdapter(private val albumPic : String, private val lifecycleOwner: LifecycleOwner, private val likeListener: LikeListener) : RecyclerView.Adapter<AlbumSingleAdapter.ItemViewHolder>() {
    private var items = emptyList<Data?>()
    private lateinit var mediaPlayer : MediaPlayer

    inner class ItemViewHolder(private val binding : CardviewTracksBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Data?)
        {

            val df = DecimalFormat("#.00")
            val duration = df.format((item?.duration)?.toDouble()?.div(60.0))

            binding.apply{
                Picasso.get()
                    .load(albumPic)
                    .into(imgTracksPic)
                txtTrackName.text = item?.title
                txtDuration.text = "$duration\""


            root.setOnClickListener(){
                relaseMediaPlayer()
                lifecycleOwner.lifecycleScope.launch() {
                    playTrack(item!!.preview)
                    }
                }

            btnLike.setOnClickListener()
            {
                lifecycleOwner.lifecycleScope.launch(){
                    val newLike = TracksLikesModel(item!!.id, item!!.title, duration, albumPic)
                    likeListener.likeTrack(newLike)
                    Log.e("Liked list:", likeListener.getLikes().value.toString())
                }

            }




            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int, ): AlbumSingleAdapter.ItemViewHolder {
        val binding = CardviewTracksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumSingleAdapter.ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateItems(newItem : List<Data?>){
        items = newItem
        notifyDataSetChanged()
    }

    private suspend fun playTrack(url : String) = withContext(Dispatchers.IO){
        mediaPlayer = MediaPlayer().apply{
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(url)
            prepare()
            start()
        }
    }

    fun relaseMediaPlayer() // call it onDestroy
    {
        if(::mediaPlayer.isInitialized){
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }
}