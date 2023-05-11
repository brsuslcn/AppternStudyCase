package com.example.appternstudycase.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appternstudycase.data.model.sql_likes_model.TracksLikesModel
import com.example.appternstudycase.databinding.ActivityAlbumSingleBinding
import com.example.appternstudycase.ui.adapter.AlbumSingleAdapter
import com.example.appternstudycase.ui.adapter.LikeListener
import com.example.appternstudycase.ui.viewmodel.AlbumSingleViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AlbumSingle : AppCompatActivity() , LikeListener {
    private lateinit var binding: ActivityAlbumSingleBinding
    private val viewModel : AlbumSingleViewModel by viewModels()
    private lateinit var albumSingleAdapter: AlbumSingleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumSingleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val albumName = intent.getStringExtra("albumName")
        val albumId = intent.getIntExtra("albumId",0)
        val albumPic = intent.getStringExtra("albumPic")


        viewModel.songs(albumId)
        albumSingleAdapter = AlbumSingleAdapter(albumPic.toString(), this,this)


        binding.apply {
            txtAlbumName.text = albumName
            recyclerView.layoutManager = LinearLayoutManager(applicationContext)
            recyclerView.adapter = albumSingleAdapter
        }

        viewModel.tracksLiveData.observe(this, Observer { tracks ->
            albumSingleAdapter.updateItems(tracks)
        })

        val job = CoroutineScope(Dispatchers.Main).launch()
        {
          /*  val newLike = SqlLikesModel(1233666998,"deneme","denmee","denemepic")
            viewModel.likeTrack(newLike)*/

        }

        viewModel.dbLikes()
    }

    override fun onDestroy() {
        super.onDestroy()
        albumSingleAdapter.relaseMediaPlayer()
    }

    override fun likeTrack(track: TracksLikesModel) {
        viewModel.likeTrack(track)
    }

    override fun dislikeTrack(track: TracksLikesModel) {
        viewModel.dislikeTrack(track)
    }

    override fun getLikes(): MutableLiveData<List<TracksLikesModel>> {
        return viewModel.likedData
    }
}