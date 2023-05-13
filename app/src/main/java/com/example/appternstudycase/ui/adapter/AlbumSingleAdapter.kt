package com.example.appternstudycase.ui.adapter


import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.appternstudycase.R
import com.example.appternstudycase.data.model.sql_likes_model.TracksLikesModel
import com.example.appternstudycase.data.model.tracks_model.Data
import com.example.appternstudycase.databinding.CardviewTracksBinding
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.DecimalFormat

class AlbumSingleAdapter(private val albumPic : String, private val lifecycleOwner: LifecycleOwner, private val likeListener: LikeListener) : RecyclerView.Adapter<AlbumSingleAdapter.ItemViewHolder>() {
    private var items = emptyList<Data?>()
    private lateinit var mediaPlayer : MediaPlayer
    var isLiked = false
    var isInLikes = false


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



                likeListener.getLikes().observe(lifecycleOwner, Observer { likes ->
                    isLiked =likes?.any(){it.track_title == item?.title}!!
                    if (isLiked) {
                        btnLike.setImageResource(R.drawable.heart_icon_fill)
                    } else {
                        btnLike.setImageResource(R.drawable.heart_icon)
                    }
                })

            root.setOnClickListener(){
                relaseMediaPlayer()
                lifecycleOwner.lifecycleScope.launch() {
                    playTrack(item!!.preview)
                    }
                }

            btnLike.setOnClickListener()
            {

                    likeListener.getLikes().observe(lifecycleOwner, Observer {likes->
                        isInLikes = likes?.any(){it.track_title==item?.title}!!
                    })

                    val processLike = TracksLikesModel(item!!.id, item!!.title, duration, albumPic)

                    if(!isInLikes)
                    {
                        likeListener.likeTrack(processLike)
                        btnLike.setImageResource(R.drawable.heart_icon_fill)
                    }

                    else
                    {
                        Snackbar.make(it, "Are you sure to remove in likes?", Snackbar.LENGTH_LONG)
                            .setAction("YES"){
                                likeListener.dislikeTrack(processLike)
                                btnLike.setImageResource(R.drawable.heart_icon)
                            }.show()

                    }
                }
            }

            isLiked=false
            isInLikes=false
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