package com.example.appternstudycase.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appternstudycase.databinding.ActivityAlbumSingleBinding
import com.example.appternstudycase.ui.adapter.AlbumSingleAdapter
import com.example.appternstudycase.ui.viewmodel.AlbumSingleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumSingle : AppCompatActivity() {
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
        albumSingleAdapter = AlbumSingleAdapter(albumPic.toString(), this)


        binding.apply {
            txtAlbumName.text = albumName
            recyclerView.layoutManager = LinearLayoutManager(applicationContext)
            recyclerView.adapter = albumSingleAdapter
        }

        viewModel.tracksLiveData.observe(this, Observer { tracks ->
            albumSingleAdapter.updateItems(tracks)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        albumSingleAdapter.relaseMediaPlayer()
    }
}